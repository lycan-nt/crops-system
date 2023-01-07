package com.owl.systems.crops.controller;

import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<Event>> findAll() throws Exception {
        List<Event> eventList = this.eventService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(eventList);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched event"),
            @ApiResponse(code = 403, message = "Unauthorized user"),
            @ApiResponse(code = 500, message = "Error in the request")
    })
    @ApiOperation("Search event")
    public ResponseEntity<Event> find(
           @ApiParam(value = "event id") @PathVariable("id") Integer id
    ) {
        Event event = this.eventService.find(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success in creating the event"),
            @ApiResponse(code = 403, message = "Unauthorized user"),
            @ApiResponse(code = 500, message = "Error in the request")
    })
    @ApiOperation("creating the event")
    public ResponseEntity<Event> insert(
            @ApiParam(value = "Event") @Valid @RequestBody Event event
    ) {
        this.eventService.insert(event);
        return new ResponseEntity<Event>(event, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success in update the event"),
            @ApiResponse(code = 403, message = "Unauthorized user"),
            @ApiResponse(code = 500, message = "Error in the request")
    })
    @ApiOperation("Update the event")
    public ResponseEntity<Event> update(
            @ApiParam(value = "Event") @Valid @RequestBody Event event
    ) {
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

}
