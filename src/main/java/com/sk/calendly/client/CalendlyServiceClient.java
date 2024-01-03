/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.client;

import com.sk.calendly.client.internal.WebRequest;
import com.sk.calendly.client.internal.ServiceClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.calendly.dto.AvailabilityResponse;
import com.sk.calendly.dto.CreateEventResponse;
import com.sk.calendly.dto.CreateScheduleRequest;
import com.sk.calendly.dto.CreateScheduleResponse;
import com.sk.calendly.dto.DayOfWeek;
import com.sk.calendly.dto.Interval;
import com.sk.calendly.dto.Schedule;
import com.sk.calendly.dto.ScheduleResponse;
import com.sk.calendly.dto.UserEventResponse;
import com.sk.calendly.dto.UserOverlapResponse;
import com.sk.calendly.entity.UserEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sushant.kumar
 */
public class CalendlyServiceClient extends ServiceClient {

    private static final ObjectMapper mapper = new ObjectMapper();

    public CalendlyServiceClient(String baseUrl, int timeout) {
        super(baseUrl, timeout);
    }

    public AvailabilityResponse getAvailability(int userId, int date) {
        TypeReference<AvailabilityResponse> typeRef = new TypeReference<AvailabilityResponse>() {
        };
        WebRequest<AvailabilityResponse> req = new WebRequest<>(typeRef, "/availability/" + userId + "/" + date);
        return executeGet(req);
    }

    public ScheduleResponse getSchedule(int userId) {
        TypeReference<ScheduleResponse> typeRef = new TypeReference<ScheduleResponse>() {
        };
        WebRequest<ScheduleResponse> req = new WebRequest<>(typeRef, "/schedule/" + userId);
        return executeGet(req);
    }

    public UserEventResponse getEvents(int userId, int date) {
        TypeReference<UserEventResponse> typeRef = new TypeReference<UserEventResponse>() {
        };
        WebRequest<UserEventResponse> req = new WebRequest<>(typeRef, "/events/" + userId+"?date="+date);
        return executeGet(req);
    }

    public UserOverlapResponse getOverlaps(int userId1, int userId2, int date) {
        TypeReference<UserOverlapResponse> typeRef = new TypeReference<UserOverlapResponse>() {
        };
        WebRequest<UserOverlapResponse> req = new WebRequest<>(typeRef, "/events/overlap/" + userId1 + "/" + userId2 + "?date=" + date);
        return executeGet(req);
    }

    public CreateEventResponse createEvent(UserEvent event) {
        TypeReference<CreateEventResponse> typeRef = new TypeReference<CreateEventResponse>() {
        };
        WebRequest<CreateEventResponse> req = new WebRequest<>(typeRef, "/events");
        req.setPayload(event);
        return executePost(req);
    }

    public CreateScheduleResponse createUserSchedule(CreateScheduleRequest request, int userId) {
        TypeReference<CreateScheduleResponse> typeRef = new TypeReference<CreateScheduleResponse>() {
        };
        WebRequest<CreateScheduleResponse> req = new WebRequest<>(typeRef, "/schedule/" + userId);
        req.setPayload(request);
        return executePost(req);
    }

    public static void main(String[] args) throws Exception {
        CalendlyServiceClient client = new CalendlyServiceClient("http://18.61.72.193:8082", 1000);

        Schedule schedule0 = new Schedule(DayOfWeek.MONDAY, new Interval(10, 30, 18, 30));
        Schedule schedule1 = new Schedule(DayOfWeek.TUESDAY, new Interval(10, 30, 18, 30));
        Schedule schedule2 = new Schedule(DayOfWeek.WEDNESDAY, new Interval(10, 30, 18, 30));
        Schedule schedule3 = new Schedule(DayOfWeek.THURSDAY, new Interval(10, 30, 18, 30));
        Schedule schedule4 = new Schedule(DayOfWeek.FRIDAY, new Interval(10, 30, 18, 30));
        List<Schedule> scheduleList = new ArrayList<>();
        CreateScheduleRequest request = new CreateScheduleRequest();
        scheduleList.add(schedule0);
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);
        scheduleList.add(schedule3);
        scheduleList.add(schedule4);
        request.setSchedules(scheduleList);
        CreateScheduleResponse createUserSchedule = client.createUserSchedule(request, 11);
        System.out.println("Create Schedule response -> " + mapper.writeValueAsString(createUserSchedule));
        ScheduleResponse resp = client.getSchedule(1);
        System.out.println("Schedule -> " + mapper.writeValueAsString(resp));
        AvailabilityResponse availabilityResponse = client.getAvailability(1, 1704133800);
        System.out.println("Availability -> " + mapper.writeValueAsString(availabilityResponse));
        UserEventResponse events = client.getEvents(1,1704133800);
        System.out.println("Events -> " + mapper.writeValueAsString(events));
        UserOverlapResponse overlaps = client.getOverlaps(1, 2, 1704133800);
        System.out.println("Overlaps -> " + mapper.writeValueAsString(overlaps));

        UserEvent event = new UserEvent(10, 1704133800, 16, 0, 16, 30, "Meeting with Boss");
        CreateEventResponse createdEvent = client.createEvent(event);

        System.out.println("Create Event response -> " + mapper.writeValueAsString(createdEvent));

    }
}
