package com.mywork.footballteams;

import com.mywork.footballteams.exception.FootBallTeamDataException;
import com.mywork.footballteams.model.FootBallTeam;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by prabeenjayaraj on 22/06/2017.
 */
public class FootBallTeamBuilderTest {


    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Test to Make sure that we dont build an FootBallTeam without the required details
     * @throws FootBallTeamDataException
     */
    @Test
    public void onTestFootBallTeamWBuilerWithNoData() throws FootBallTeamDataException {
        exception.expect(com.mywork.footballteams.exception.FootBallTeamDataException.class);
        exception.expectMessage("TeamName Is Null");
        FootBallTeam footBallTeam = FootBallTeam.builder().build();
    }
    @Test
    public void onTestFootBallTeamBuiderWithNoTeamName() throws FootBallTeamDataException{
        exception.expect(com.mywork.footballteams.exception.FootBallTeamDataException.class);
        exception.expectMessage("TeamName Is Null");
        FootBallTeam footBallTeam = FootBallTeam.builder().setCity("Belfast").setCompetition("UFEA").setNoOfPlayers(20)
        .setOwner("BelfastOWner").setStadiumCapacity(Long.valueOf(30000)).build();
    }

    @Test
    public void onTestFootBallTeamBuiderWithNoCity() throws FootBallTeamDataException{
        exception.expect(com.mywork.footballteams.exception.FootBallTeamDataException.class);
        exception.expectMessage("City Is Null");
        FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiants").setCompetition("UFEA").setNoOfPlayers(20)
                .setOwner("BelfastOWner").setStadiumCapacity(Long.valueOf(30000)).build();
    }

    @Test
    public void onTestFootBallTeamBuiderWithNoOwner() throws FootBallTeamDataException{
        exception.expect(com.mywork.footballteams.exception.FootBallTeamDataException.class);
        exception.expectMessage("Owner Is Null");
        FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiants").
                setCity("Belfast").setCompetition("UFEA").setNoOfPlayers(20)
                .setStadiumCapacity(Long.valueOf(30000)).build();
    }


    @Test
    public void onTestFootBallTeamBuiderWithNoCompetition() throws FootBallTeamDataException{
        exception.expect(com.mywork.footballteams.exception.FootBallTeamDataException.class);
        exception.expectMessage("Competition Is Null");
        FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiants").
                setCity("Belfast").setNoOfPlayers(20)
                .setOwner("BelfastOWner")
                .setStadiumCapacity(Long.valueOf(30000)).build();
    }

    @Test
    public void onTestFootBallTeamBuiderWithNoPlayers() throws FootBallTeamDataException{
        exception.expect(com.mywork.footballteams.exception.FootBallTeamDataException.class);
        exception.expectMessage("NoOfPlayers Is Too Less To Create a Team");
        FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiants").
                setCompetition("UFEA")
                .setCity("Belfast")
                .setOwner("BelfastOWner")
                .setStadiumCapacity(Long.valueOf(30000)).build();
    }

    @Test
    public void onTestFootBallTeamBuiderWithMoreNolayers() throws FootBallTeamDataException{
        FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiants").
                setCompetition("UFEA")
                .setCity("Belfast")
                .setNoOfPlayers(30)
                .setOwner("BelfastOWner")
                .setStadiumCapacity(Long.valueOf(30000)).build();
    }

    @Test
    public void onTestFootBallTeamBuiderWithNoStatiumCapacity() throws FootBallTeamDataException{

        exception.expect(com.mywork.footballteams.exception.FootBallTeamDataException.class);
        exception.expectMessage("Stadium Capcity Cant be Less than 100");
        FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiants").
                setCompetition("UFEA")
                .setCity("Belfast")
                .setNoOfPlayers(30)
                .setOwner("BelfastOWner")
//                .setStadiumCapacity(Long.valueOf(30000)).
                .build();
    }

    @Test
    public void onTestFootBallTeamBuiderWithMoreStatiumCapacity() throws FootBallTeamDataException{

        FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiants").
                setCompetition("UFEA")
                .setCity("Belfast")
                .setNoOfPlayers(30)
                .setOwner("BelfastOWner")
                .setStadiumCapacity(Long.valueOf(30000))
                .build();
    }


    @Test
    public void onTestFootBallTeamBuiderWithAllData() throws FootBallTeamDataException{

        FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiants").
                setCompetition("UFEA")
                .setCity("Belfast")
                .setNoOfPlayers(30)
                .setOwner("BelfastOWner")
                .setStadiumCapacity(Long.valueOf(30000))
                .build();

        Assert.assertEquals(footBallTeam.getCity(),"Belfast");
        Assert.assertEquals(footBallTeam.getTeamName(),"BelfastGiants");
        Assert.assertEquals(footBallTeam.getCompetition(),"UFEA");
        Assert.assertEquals(footBallTeam.getOwner(),"BelfastOWner");
        Assert.assertEquals(footBallTeam.getStadiumCapacity(),Long.valueOf(30000));



    }


}
