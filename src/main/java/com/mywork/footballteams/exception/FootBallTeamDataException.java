package com.mywork.footballteams.exception;


/**
 * This is the custom data validation exception that will be thrown
 * if and invalid data is passed for creating the team
 * Created by prabeenjayaraj on 23/06/2017.
 */
public class FootBallTeamDataException extends Exception {

    public FootBallTeamDataException(String field, String msg){
        super("Data Validation Occurred For the Field " + field + " " + msg);
    }
}
