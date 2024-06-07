package com.ecart.ecart.exception;

import com.ecart.ecart.dto.MyErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 404 Not Found
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<MyErrorResponse> handleEntityNotFoundException(EntityNotFoundException e, WebRequest re){
        MyErrorResponse myErrorResponse = new MyErrorResponse(404, e.getMessage());
        log.error("Entity not found Exception raised with: "+e.getMessage());
        return new ResponseEntity<>(myErrorResponse, HttpStatus.NOT_FOUND);
    }


    // 409 Conflict
    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<MyErrorResponse> handleEntityAlreadyExistsException(EntityAlreadyExistsException e, WebRequest re){
        MyErrorResponse myErrorResponse = new MyErrorResponse(409, e.getMessage());
        log.error("Entity already exists Exception raised with: "+e.getMessage());
        return new ResponseEntity<>(myErrorResponse, HttpStatus.CONFLICT);
    }


    // 400 Bad Request
    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<MyErrorResponse> handleInvalidInputException(InvalidInputException e, WebRequest re){
        MyErrorResponse myErrorResponse = new MyErrorResponse(400, e.getMessage());
        log.error("Invalid input Exception raised with: "+e.getMessage());
        return new ResponseEntity<>(myErrorResponse, HttpStatus.BAD_REQUEST);
    }

    // other
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MyErrorResponse> handleAll(Exception e){
        MyErrorResponse myErrorResponse = new MyErrorResponse(500, e.getMessage());
        log.error("Exception raised with: "+e.getMessage());
        return new ResponseEntity<>(myErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
