package com.appdeveloperblog.mobile_app_ws.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

/*
    if any field is invalid,
    The default 400 Bad Request will be sent along with the mentioned error message like message = "First name cannot be null"
 */


public class UserDetailsRequestModel {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must be at least 2 characters")
    private String firstName;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "Last name must be at least 2 characters")
    private String lastName;

    @NotNull(message = "First name cannot be null")
    @Email
    private String email;

    @NotNull(message = "First name cannot be null")
    @Size(min = 8, max = 16, message = "Password must be greater than or equal to 8 characters and less than 16 characters")
    private String password;
}
