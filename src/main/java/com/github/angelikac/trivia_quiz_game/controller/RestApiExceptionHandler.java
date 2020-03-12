package com.github.angelikac.trivia_quiz_game.controller;

import com.github.angelikac.trivia_quiz_game.dto.ApiErrorDto;
import com.github.angelikac.trivia_quiz_game.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiErrorDto> handleEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(create(exception));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorDto> handleGenericException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(create(exception));
    }

    private ApiErrorDto create(Exception exception) {
        ApiErrorDto errorDto = new ApiErrorDto();
        errorDto.setExceptionClass(exception.getClass().getCanonicalName());
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
}
