package com.appdeveloperblog.mobile_app_ws.service;

import com.appdeveloperblog.mobile_app_ws.model.request.UserDetailsRequestModel;
import com.appdeveloperblog.mobile_app_ws.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}
