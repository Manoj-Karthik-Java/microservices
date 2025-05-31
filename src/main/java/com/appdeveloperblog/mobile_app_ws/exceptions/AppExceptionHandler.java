package com.appdeveloperblog.mobile_app_ws.exceptions;

import com.appdeveloperblog.mobile_app_ws.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/*
If you do not extend ResponseEntityExceptionHandler in your @ControllerAdvice class,
your custom @ExceptionHandler method will still work exactly the same for the Exception.class type you're explicitly handling.

However, you lose access to the default exception handling logic that ResponseEntityExceptionHandler provides for various Spring MVC exceptions.

| Exception Type                            | Default Handler in `ResponseEntityExceptionHandler` |
| ----------------------------------------- | --------------------------------------------------- |
| `MethodArgumentNotValidException`         | `handleMethodArgumentNotValid`                      |
| `HttpRequestMethodNotSupportedException`  | `handleHttpRequestMethodNotSupported`               |
| `HttpMediaTypeNotSupportedException`      | `handleHttpMediaTypeNotSupported`                   |
| `MissingServletRequestParameterException` | `handleMissingServletRequestParameter`              |

 */


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e) {
        String errorMessageDescription = e.getLocalizedMessage();
        if (errorMessageDescription == null) errorMessageDescription = e.toString();
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



//    @ExceptionHandler(value = {NullPointerException.class})
//    public ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
//        String errorMessageDescription = e.getLocalizedMessage();
//        if (errorMessageDescription == null) errorMessageDescription = e.toString();
//        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
//        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(value = {UserServiceException.class})
//    public ResponseEntity<Object> handleUserServiceException(UserServiceException e) {
//        String errorMessageDescription = e.getLocalizedMessage();
//        if (errorMessageDescription == null) errorMessageDescription = e.toString();
//        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
//        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    // We can combine the above two methods into one if we have the same function body

    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity<Object> handleNullPointerException(Exception e) {
        String errorMessageDescription = e.getLocalizedMessage();
        if (errorMessageDescription == null) errorMessageDescription = e.toString();
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
/*

Difference between @ControllerAdvice and @RestControllerAdvice

@ControllerAdvice -
 1.Introduced to provide global handling for controllers (across all or specific packages).
 2.Works with both @Controller and @RestController.
 3.Methods annotated with @ExceptionHandler, @ModelAttribute, or @InitBinder will apply to all controllers.
 4.Does not automatically serialize response bodies to JSON or XML. You need to use @ResponseBody on methods if you want a JSON response.
 5.If your controller or exception handler returns an object, Spring tries to automatically serialize it, even without @ResponseBody.

@RestControllerAdvice -
 1.Introduced to provide global handling for controllers (across all or specific packages).
 2.Works with both @Controller and @RestController.
 3.Methods annotated with @ExceptionHandler, @ModelAttribute, or @InitBinder will apply to all controllers.
 4.Does not automatically serialize response bodies to JSON or XML. You need to use @ResponseBody on methods if you want a JSON response.

 Use @RestControllerAdvice when you’re building a RESTful service and want consistent JSON responses.
 Use @ControllerAdvice when you're returning views (e.g., HTML pages) or a mix of view and API responses.

 */