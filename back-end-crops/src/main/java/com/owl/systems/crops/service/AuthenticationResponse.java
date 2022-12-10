package com.owl.systems.crops.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
    private String userName;
}
