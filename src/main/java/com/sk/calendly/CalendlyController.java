package com.sk.calendly;

import com.sk.calendly.dto.CreateScheduleRequest;
import com.sk.calendly.dto.DayOfWeek;
import com.sk.calendly.dto.OverlapRequestParams;
import com.sk.calendly.entity.UserEvent;
import com.sk.calendly.service.CalendlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CalendlyController {
    
        @Autowired
        private CalendlyService calendlyService;

	@GetMapping("schedule/{id}")
	public ResponseEntity getUserSchedule(@PathVariable("id") int id) {
            return ResponseEntity.ok(calendlyService.getSchedule(id));
	}
        
        
        @GetMapping("events/{id}")
	public ResponseEntity getUserEvents(@PathVariable("id") int id,
                @RequestParam("date") int date) {
            return ResponseEntity.ok(calendlyService.getEvents(id,date));
	}
        
        @GetMapping("availability/{id}/{date}")
	public ResponseEntity getUserAvailability(@PathVariable("id") int id, 
                @PathVariable("date") int date) {
            return ResponseEntity.ok(calendlyService.getAvailability(id,date));
	}
        
        
        @GetMapping("events/overlap/{user1}/{user2}")
	public ResponseEntity findOverlap(@PathVariable("user1") int user1, 
                @PathVariable("user2") int user2, @RequestParam("date") int date) {
            OverlapRequestParams param = new OverlapRequestParams(user1, user2, date);
            return ResponseEntity.ok(calendlyService.findOverlap(param));
	}
        
        @GetMapping("schedule/overlap/{user1}/{user2}")
	public ResponseEntity findOverlapSchedule(@PathVariable("user1") int user1, 
                @PathVariable("user2") int user2, @RequestParam("dayOfWeek") DayOfWeek dayOfWeek) {
            return ResponseEntity.ok(calendlyService.findCommonSchedule(user1,user2,dayOfWeek));
	}
        
        @PostMapping("events")
	public ResponseEntity createUserEvents(@RequestBody UserEvent event) {
            return ResponseEntity.ok(calendlyService.createEvent(event));
	}
        
        @PostMapping("schedule/{userId}")
	public ResponseEntity createUserSchedule(@PathVariable("userId") int userId, @RequestBody CreateScheduleRequest request) {
            return ResponseEntity.ok(calendlyService.createSchedule(request,userId));
	}
        
}
