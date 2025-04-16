package com.appdeveloperblog.mobile_app_ws.controller;

import com.appdeveloperblog.mobile_app_ws.model.response.UserRest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

//    Below annotation can also be written as @GetMapping("/{userId}")
//    produces property is used to produce return data in xml format
    @GetMapping(path = "/{userId}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable String userId){
        UserRest userRest = new UserRest();
        userRest.setEmail("test@test.com");
        userRest.setFirstName("manoj");
        userRest.setLastName("karthik");
        return userRest;
    }

    /*
        If we want optional value for query parameters then we need to use 'required = false' property
        we need to assign default value for query params because in the below example page and limit are primitives,
        primitives cannot be assigned to null.
        If we do not want to assign default value it is safe to not use primitives while defining query params
     */
    @GetMapping
    public String getUsers(
            @RequestParam(value = "page",defaultValue = "1") int page,
            @RequestParam(value = "limit",defaultValue = "50") int limit,
            @RequestParam(value = "sort",defaultValue = "desc",required = false) String sort
    ){
        return page + " " + limit;
    }

}
