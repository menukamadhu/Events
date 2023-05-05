package com.example.event.controller;

import com.example.event.dto.EventsDTO;
import com.example.event.dto.PayLoadDTO;
import com.example.event.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping({"api/events"})
public class EventsController {
    @Autowired
    private EventsService eventsService;

    @PostMapping({"/addevent"})
    public ResponseEntity addEvent(@RequestBody EventsDTO eventsDTO) {
        Map<String, Object> map = new LinkedHashMap();
        EventsDTO res = eventsService.addEvent(eventsDTO);
        if (res != null) {
            map.put("status", 1);
            map.put("message", "Success");
            map.put("data", res);
            return new ResponseEntity(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Event not added");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping({"/getallevents"})
    public ResponseEntity getAllEvents() {
        Map<String, Object> map = new LinkedHashMap();
        List<EventsDTO> list = eventsService.getAllEvents();
        if (!list.isEmpty()) {
            map.put("status", 1);
            map.put("message", "Success");
            map.put("data", list);
            return new ResponseEntity(map, HttpStatus.ACCEPTED);
        } else {
            map.put("status", 0);
            map.put("message", "Event list is not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping({"/getevent/{eventId}"})
    public ResponseEntity getEventById(@PathVariable UUID eventId) {
        Map<String, Object> map = new LinkedHashMap();
        EventsDTO eventsDTO = eventsService.getEventById(eventId);
        if (eventsDTO != null) {
            map.put("status", 1);
            map.put("message", "Success");
            map.put("data", eventsDTO);
            return new ResponseEntity(map, HttpStatus.ACCEPTED);
        } else {
            map.put("status", 0);
            map.put("message", "Package list is not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping({"/updateevent"})
    public ResponseEntity updateEvent(@RequestBody EventsDTO eventsDTO) {
        Map<String, Object> map = new LinkedHashMap();
        String res = eventsService.updateEvent(eventsDTO);
        if (res.equals("Event updated successfully")) {
            map.put("status", 1);
            map.put("message", "Success");
            map.put("data", res);
            return new ResponseEntity(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Event not updated");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping({"/deleteevent/{eventId}"})
    public ResponseEntity deleteEvent(@PathVariable UUID eventId) {
        Map<String, Object> map = new LinkedHashMap();
        String res = eventsService.deleteEvent(eventId);
        if (res.equals("Event Deleted successfully")) {
            map.put("status", 1);
            map.put("message", "Success");
            map.put("data", res);
            return new ResponseEntity(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Event not deleted");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/batch"})
    public ResponseEntity saveBatchEvent(@RequestBody PayLoadDTO payloadDTO) {
        return ResponseEntity.ok(this.eventsService.saveEvent(payloadDTO));
    }

}
