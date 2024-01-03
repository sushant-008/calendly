/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.service;

import com.sk.calendly.dto.AvailabilityResponse;
import com.sk.calendly.dto.CommonScheduleResponse;
import com.sk.calendly.dto.CreateEventResponse;
import com.sk.calendly.dto.CreateScheduleRequest;
import com.sk.calendly.dto.CreateScheduleResponse;
import com.sk.calendly.dto.DayOfWeek;
import com.sk.calendly.dto.Interval;
import com.sk.calendly.dto.OverlapRequestParams;
import com.sk.calendly.dto.OverlapResponse;
import com.sk.calendly.dto.Schedule;
import com.sk.calendly.dto.ScheduleResponse;
import com.sk.calendly.dto.Slot;
import com.sk.calendly.dto.SlotType;
import com.sk.calendly.dto.UserEventResponse;
import com.sk.calendly.dto.UserOverlapResponse;
import com.sk.calendly.entity.UserEvent;
import com.sk.calendly.entity.UserSchedule;
import com.sk.calendly.repository.EventRepository;
import com.sk.calendly.repository.ScheduleRepository;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sushant
 */
@Service
public class CalendlyService {
    
    
    private Map<Integer,DayOfWeek> map = new HashMap<>();
    
    @PostConstruct
    void init() {
        map.put(1, DayOfWeek.MONDAY);
        map.put(2, DayOfWeek.TUESDAY);
        map.put(3, DayOfWeek.WEDNESDAY);
        map.put(4, DayOfWeek.THURSDAY);
        map.put(5, DayOfWeek.FRIDAY);
        map.put(6, DayOfWeek.SATURDAY);
        map.put(7, DayOfWeek.SUNDAY);
    }

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EventRepository eventRepository;

    public CreateScheduleResponse createSchedule(CreateScheduleRequest request, int userId) {
        List<UserSchedule> schedules = scheduleRepository.getByUserId(userId);
        Map<DayOfWeek,UserSchedule> map = schedules.stream().collect(Collectors.toMap(UserSchedule::getDayName, Function.identity()));
        CreateScheduleResponse response = new CreateScheduleResponse();
        List<Schedule> createdSchedules = new ArrayList<>();
        response.setSuccess(false);
        for(Schedule sc : request.getSchedules()) {
            if(!sc.validate()) {
                response.setErrorMessage("Invalid schedule definition");
                return response;
            }
        }
        for (Schedule sc : request.getSchedules()) {
            UserSchedule us = map.getOrDefault(sc.getDayName(), new UserSchedule());
            us.setDayName(sc.getDayName());
            us.setFromHour(sc.getInterval().getFromHour());
            us.setFromMinutes(sc.getInterval().getFromMinutes());
            us.setToHour(sc.getInterval().getToHour());
            us.setToMinutes(sc.getInterval().getToMinutes());
            us.setUserId(userId);
            UserSchedule createdSchedule = scheduleRepository.save(us);
            createdSchedules.add(new Schedule(createdSchedule.getDayName(), new Interval(createdSchedule.getFromHour(), createdSchedule.getFromMinutes(), createdSchedule.getToHour(), createdSchedule.getToMinutes())));
        }
        response.setSchedules(createdSchedules);
        response.setSuccess(true);
        return response;
    }

    public ScheduleResponse getSchedule(int userId) {
        List<UserSchedule> schedules = scheduleRepository.getByUserId(userId);
        List<Schedule> result = new ArrayList<>();
        for (UserSchedule us : schedules) {
            result.add(new Schedule(us.getDayName(), new Interval(us.getFromHour(), us.getFromMinutes(), us.getToHour(), us.getToMinutes())));
        }
        Collections.sort(result);
        ScheduleResponse resp = new ScheduleResponse();
        resp.setSchedules(result);
        return resp;
    }

    public UserEventResponse getEvents(int userId, int date) {
        List<UserEvent> events =  eventRepository.getByUserId(userId, date);
        UserEventResponse resp = new UserEventResponse();
        resp.setEvents(events);
        return resp;
    }

    

    public CreateEventResponse createEvent(UserEvent event) {
        CreateEventResponse createEventResponse= new CreateEventResponse();
        createEventResponse.setSuccess(false);
        if(!event.getInterval().isValid()) {
            createEventResponse.setErrorMessage("Invalid durations");
            return createEventResponse;
        }
        
        int dayCode = dateToDay(event.getEventDate());
        DayOfWeek dayOfWeek = map.get(dayCode);
        UserSchedule schedule = scheduleRepository.getByUserId(event.getUserId(), dayOfWeek);
        if(schedule==null) {
            createEventResponse.setErrorMessage("User schedule not found");
            return createEventResponse;
        }
        if(event.getInterval().getLeftMinutes()< schedule.getInterval().getLeftMinutes() || 
                event.getInterval().getRightMinutes()>schedule.getInterval().getRightMinutes()) {
            createEventResponse.setErrorMessage("Event timings are beyond user schedule");
            return createEventResponse;
            
        }
        
        List<UserEvent> userEvents = eventRepository.getByUserId(event.getUserId(), event.getEventDate());
        for(UserEvent ue : userEvents) {
            if(ue.getInterval().isOverlapping(event.getInterval())) {
                createEventResponse.setErrorMessage("Conflict");
                createEventResponse.setUserEvent(event);
                return createEventResponse;
            }
        }
        
        UserEvent createdEvent =  eventRepository.save(event);
        createEventResponse.setSuccess(true);
        createEventResponse.setUserEvent(createdEvent);
        return createEventResponse;
    }
    
    
    public CommonScheduleResponse findCommonSchedule(int user1, int user2, DayOfWeek dayOfWeek) {
        CommonScheduleResponse resp = new CommonScheduleResponse();
        UserSchedule schedule1 = scheduleRepository.getByUserId(user1, dayOfWeek);
        UserSchedule schedule2 = scheduleRepository.getByUserId(user2, dayOfWeek);
        if(schedule1==null) {
            resp.setErrorMessage("User "+user1+" has no schedules for "+dayOfWeek.name());
            resp.setSuccess(false);
            return resp;
        } else if(schedule2==null) {
            resp.setErrorMessage("User "+user2+" has no schedules for "+dayOfWeek.name());
            resp.setSuccess(false);
            return resp;
        }
        Interval result;
        if(schedule1.getInterval().compareTo(schedule2.getInterval())>0) {
            result= getCommonSchedule(schedule1,schedule2);
        } else {
            result = getCommonSchedule(schedule2,schedule1);
        }
        
        resp.setCommonInterval(result);
        resp.setSuccess(true);
        return resp;
        
    }
    
    
    private Interval getCommonSchedule(UserSchedule us1, UserSchedule us2) {
        int startHr = Math.max(us1.getInterval().getFromHour(), us2.getInterval().getFromHour());
        int startMin = Math.max(us1.getInterval().getFromMinutes(), us2.getInterval().getFromMinutes());
        int endHr = Math.min(us1.getInterval().getToHour(), us2.getInterval().getToHour());
        int endMin = Math.min(us1.getInterval().getToMinutes(), us2.getInterval().getToMinutes());
        return new Interval(startHr, startMin, endHr, endMin);
    }
    
    
    public UserOverlapResponse findOverlap(OverlapRequestParams param) {
        List<UserEvent> listOne = eventRepository.getByUserId(param.getUserOne(), param.getDay());
        List<UserEvent> listTwo = eventRepository.getByUserId(param.getUserTwo(), param.getDay());
        List<OverlapResponse> result = new ArrayList<>();
        
        for(UserEvent ue1 : listOne) {
            for(UserEvent ue2 : listTwo) {
                if(ue1.getInterval().isOverlapping(ue2.getInterval())) {
                    OverlapResponse resp = new OverlapResponse(ue1, ue2);
                    result.add(resp);
                }
            }
        }
        UserOverlapResponse resp = new UserOverlapResponse();
        resp.setOverlaps(result);
        return resp;
        
    }
    
    public AvailabilityResponse getAvailability(int userId, int date) {
        AvailabilityResponse resp = new AvailabilityResponse();
        int day = dateToDay(date);
        UserSchedule schedule = scheduleRepository.getByUserId(userId,map.get(day-1));
        if(schedule==null) {
            resp.setErrorMessage("No schedule found for user");
            resp.setSuccess(false);
            return resp;
        }
        List<UserEvent> events = eventRepository.getByUserId(userId, date);
        
        resp.setSlots(splitMerge(schedule, events));
        return resp;
    }
    
    
    
    private List<Slot> splitMerge(UserSchedule schedule, List<UserEvent> events) {
        
        List<Slot> result = new ArrayList<>();
        
        if(events==null || events.isEmpty()) {
            Slot slot = new Slot(schedule.getInterval(), SlotType.FREE, "");
            result.add(slot);
            return result;
        }
        Collections.sort(events, Comparator.reverseOrder());
        
        Stack<UserEvent> stack = new Stack<>();
        for(UserEvent i : events) {
            stack.push(i);
        }
        
        int startHr = schedule.getFromHour();
        int startMin = schedule.getFromMinutes();
        
        if(!stack.isEmpty()) {
            UserEvent start = stack.peek();
            result.add(new Slot(new Interval(startHr,startMin,start.getFromHour(),start.getFromMinutes()), SlotType.FREE, ""));
            startHr = start.getToHour();
            startMin = start.getToMinutes();
        }
        
        while(!stack.isEmpty()) {
            UserEvent event = stack.pop();
            result.add(new Slot(new Interval(event.getFromHour(),event.getFromMinutes(),event.getToHour(),event.getToMinutes()), SlotType.OCCUPIED, event.getEventDescription()));
            startHr = event.getToHour();
            startMin = event.getToMinutes();
            
            if(!stack.isEmpty()) {
                UserEvent next = stack.peek();
                if(next.getFromHour()>=startHr) {
                    result.add(new Slot(new Interval(startHr,startMin,next.getFromHour(),next.getFromMinutes()), SlotType.FREE, ""));
                    startHr = next.getFromHour();
                    startMin = next.getFromMinutes();
                }
            }
        }
        
        result.add(new Slot(new Interval(startHr,startMin,schedule.getToHour(),schedule.getToMinutes()), SlotType.FREE, ""));
        
        
        return result.stream().filter(it-> !it.isEmpty()).collect(Collectors.toList());
    }
    
    private int getCurDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int)cal.getTimeInMillis();
    }
    
    private int dateToDay(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

}
