package com.appdeveloperblog.mobile_app_ws.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

//    Below annotation can also be written as @GetMapping("/{userId}")
    @GetMapping(params = "/{userId}")
    public String getUser(@PathVariable String userId){
        return "get mapping";
    }

}
