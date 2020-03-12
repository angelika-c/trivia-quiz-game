package com.github.angelikac.trivia_quiz_game.dto;


public class ApiErrorDto {

    public String exceptionClass;
    public String message;

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
