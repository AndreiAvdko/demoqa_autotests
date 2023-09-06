package models;

import lombok.Data;

@Data
public class LoginBodyModel {

    // {"email": "eve.holt@reqres.in", "password": "cityslicka"}
    String email;
    String password;
}
