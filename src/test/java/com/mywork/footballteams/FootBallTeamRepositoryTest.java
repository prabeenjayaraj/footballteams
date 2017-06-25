package com.mywork.footballteams;

import com.mywork.footballteams.exception.FootBallTeamDataException;
import com.mywork.footballteams.model.FootBallTeam;
import com.mywork.footballteams.repository.FootBallTeamRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by prabeenjayaraj on 23/06/2017.
 */

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class FootBallTeamRepositoryTest {

    @Autowired
    private FootBallTeamRepository footBallTeamRepository;


    @Test
    public void onTestCreateAndSaveFootBallTeam() throws FootBallTeamDataException {

        FootBallTeam footBallTeam =createFootBallTeam();
        footBallTeamRepository.save(footBallTeam);
        Assert.assertEquals(footBallTeam,footBallTeamRepository.findByTeamName(footBallTeam.getTeamName()));
    }

    private FootBallTeam createFootBallTeam() throws FootBallTeamDataException{


        return FootBallTeam.builder().setTeamName("BelfastGiants").
                setCompetition("UFEA")
                .setCity("Belfast")
                .setNoOfPlayers(30)
                .setOwner("BelfastOWner")
                .setStadiumCapacity(Long.valueOf(30000))
                .build();

    }
}
