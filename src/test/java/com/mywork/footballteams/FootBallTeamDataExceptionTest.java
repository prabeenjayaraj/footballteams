package com.mywork.footballteams;

import com.mywork.footballteams.exception.FootBallTeamDataException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by prabeenjayaraj on 23/06/2017.
 */
public class FootBallTeamDataExceptionTest {

//    @Rule
//    ExpectedException exception = ExpectedException.none();

    @Test
    public void onTestExceptionMessageFormat() throws Exception {

//        exception.expectMessage("Data Validation Occurred For the Field Owner Is Null");
        FootBallTeamDataException footBallTeamDataException = new FootBallTeamDataException("Owner", "Is Null");

        Assert.assertEquals("Data Validation Occurred For the Field Owner Is Null",footBallTeamDataException.getMessage());
    }
}
