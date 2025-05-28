package com.appdeveloperblog.mobile_app_ws.controller;

import com.appdeveloperblog.mobile_app_ws.model.request.UpdateUserDetailsRequestModel;
import com.appdeveloperblog.mobile_app_ws.model.request.UserDetailsRequestModel;
import com.appdeveloperblog.mobile_app_ws.model.response.UserRest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    Map<String, UserRest> users;
    //    Below annotation can also be written as @GetMapping("/{userId}")
    //    produces property is used to produce return data in xml format
    @GetMapping(
            path = "/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        System.out.println(users.get(userId));
        if (users.containsKey(userId))
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
        If we want optional value for query parameters then we need to use 'required = false' property
        we need to assign default value for query params because in the below example page and limit are primitives,
        primitives cannot be assigned to null.
        If we do not want to assign default value it is safe to not use primitives while defining query params
     */
    @GetMapping
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort
    ) {
        return page + " " + limit;
    }

    /*
        To perform validations in Java, we use the @Valid annotation.
        In the example, @Valid is used on the UserDetailsRequestModel class.
        This tells the program to check the validation rules inside that class, like @NotNull and @Size on its fields.
        If we don’t use @Valid, those rules won’t be checked.
        So, validation starts only when the compiler sees the @Valid annotation.
     */


    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) {

        UserRest userRest = new UserRest();
        userRest.setEmail(userDetailsRequestModel.getEmail());
        userRest.setFirstName(userDetailsRequestModel.getFirstName());
        userRest.setLastName(userDetailsRequestModel.getLastName());

        String key = UUID.randomUUID().toString();
        userRest.setId(key);
        if (users == null) users = new HashMap<>();
        users.put(key, userRest);

        return new ResponseEntity<UserRest>(users.get(key), HttpStatus.OK);

    }
    @PutMapping(
            path = "/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public UserRest updateUser(@PathVariable String userId,@Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetailsRequestModel ){
        UserRest storedUserRest = users.get(userId);
        storedUserRest.setFirstName(updateUserDetailsRequestModel.getFirstName());
        storedUserRest.setLastName(updateUserDetailsRequestModel.getLastName());
        users.put(userId,storedUserRest);
        return storedUserRest;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        users.remove(userId);
        for (String key : users.keySet()) {
            System.out.println("Key: " + key);
        }
        return ResponseEntity.noContent().build();
    }

}
