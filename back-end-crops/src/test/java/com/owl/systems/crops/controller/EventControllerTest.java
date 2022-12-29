package com.owl.systems.crops.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="tdd",roles={"ADMIN"})
    public void getAllEvents() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/events"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
