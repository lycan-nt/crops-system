package com.owl.systems.crops.controller;

import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched events."),
            @ApiResponse(code = 403, message = "Unauthorized user."),
            @ApiResponse(code = 500, message = "Error in the request.")
    })
    @ApiOperation("Search all events.")
    public ResponseEntity<List<Event>> findAll() {
        List<Event> eventList = this.eventService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(eventList);
    }

}
