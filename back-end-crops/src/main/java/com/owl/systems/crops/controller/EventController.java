package com.owl.systems.crops.controller;

import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;
    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

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

    @GetMapping("/filters")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched events."),
            @ApiResponse(code = 403, message = "Unauthorized user."),
            @ApiResponse(code = 500, message = "Error in the request.")
    })
    @ApiOperation("Search all events by filters.")
    public ResponseEntity<List<Event>> findAllByFilters(
            @ApiParam(value = "Type Event") @RequestParam(required = false, name = "typeEvento") int typeEvent,
            @ApiParam(value = "Initial Date") @RequestParam(required = false, name = "fromDate")
            @DateTimeFormat(pattern = DATE_PATTERN) Date fromDate,
            @ApiParam(value = "Final Date") @RequestParam(required = false, name = "toDate")
            @DateTimeFormat(pattern = DATE_PATTERN) Date toDate
            )
    {
        List<Event> eventList = new ArrayList<Event>();
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
    ) throws Exception {
        Event event = this.eventService.find(id);
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
    ) throws Exception {
        this.eventService.update(event);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success in delete the event"),
            @ApiResponse(code = 200, message = "Unauthorized user"),
            @ApiResponse(code = 200, message = "Error in the request")
    })
    @ApiOperation("Delete The Event")
    public ResponseEntity<HttpStatus> delete(
            @ApiParam(value = "Id Event")  @PathVariable("id") int id
    ) throws Exception {
        this.eventService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

}
