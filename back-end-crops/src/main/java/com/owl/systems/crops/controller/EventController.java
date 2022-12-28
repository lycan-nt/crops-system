package com.owl.systems.crops.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @GetMapping
    @ResponseBody
    public String findAll() {
        return "OK";
    }

}
