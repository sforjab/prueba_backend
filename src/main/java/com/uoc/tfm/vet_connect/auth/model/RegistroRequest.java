package com.uoc.tfm.vet_connect.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistroRequest {
    private String username;
    private String password;
    private String email;
}
