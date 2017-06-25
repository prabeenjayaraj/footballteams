package com.mywork.footballteams.exception;


public class CustomMessageType {

    private String errorMessage;

    public CustomMessageType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
