package com.appdeveloperblog.mobile_app_ws.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserRest {
    String id;
    String email;
    String firstName;
    String lastName;

}
