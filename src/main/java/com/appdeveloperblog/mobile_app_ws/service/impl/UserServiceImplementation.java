package com.appdeveloperblog.mobile_app_ws.service.impl;

import com.appdeveloperblog.mobile_app_ws.model.request.UserDetailsRequestModel;
import com.appdeveloperblog.mobile_app_ws.model.response.UserRest;
import com.appdeveloperblog.mobile_app_ws.service.UserService;
import com.appdeveloperblog.mobile_app_ws.shared.Utility;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/*
We can use @Component instead of @Service

Technical Perspective
    From a technical standpoint, @Service is a specialization of @Component, and both annotations achieve the same goal of making a class a Spring-managed bean.

Implications
    Using @Component instead of @Service for a service class won't affect the functionality of your application.

Best Practice
    However, it's generally considered a best practice to use @Service for service layer components because:

    - It clearly indicates the role of the class.
    - It improves code readability and maintainability.

- Use @Component for utility classes, helper classes, or other generic Spring-managed beans.
- Use @Service for classes that provide business logic or services to other layers.

 */
@Service
public  class UserServiceImplementation implements UserService {

    Map<String, UserRest> users;
    Utility utility;
    public UserServiceImplementation(Utility utility) {
        this.utility = utility;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetailsRequestModel) {
        UserRest userRest = new UserRest();
        userRest.setEmail(userDetailsRequestModel.getEmail());
        userRest.setFirstName(userDetailsRequestModel.getFirstName());
        userRest.setLastName(userDetailsRequestModel.getLastName());

        String key = utility.generateUserId();
        userRest.setId(key);
        if (users == null) users = new HashMap<>();
        users.put(key, userRest);
        return userRest;
    }
}
