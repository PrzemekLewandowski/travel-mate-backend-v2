package com.travelmate.message;

import com.travelmate.service.exception.RoleNotFoundException;
import com.travelmate.service.exception.SignUpRequestValidationException;
import com.travelmate.service.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler({UserNotFoundException.class, RoleNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String handleNotFoundException(RuntimeException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SignUpRequestValidationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected String handleSignUpRequestValidationException(SignUpRequestValidationException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({AuthenticationCredentialsNotFoundException.class, LockedException.class})
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    protected String handleAuthenticationCredentialsNotFoundException(RuntimeException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected String handleBadCredentialsException(BadCredentialsException exception) {
        return exception.getMessage();
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
