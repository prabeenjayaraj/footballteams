package com.mywork.footballteams;

import com.mywork.footballteams.controller.FootBallTeamController;
import com.mywork.footballteams.exception.CustomMessageType;
import com.mywork.footballteams.exception.FootBallTeamDataException;
import com.mywork.footballteams.model.FootBallTeam;
import com.mywork.footballteams.repository.FootBallTeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FootballteamsApplicationTests {

	@Autowired
	private FootBallTeamController footBallTeamController;

	@Autowired
	private FootBallTeamRepository footBallTeamRepository;
	@Test
	public void contextLoads() {
		Assertions.assertThat(footBallTeamController).isNotNull();
	}

	@Test
	public void onTestFindByTeamName(){

		ResponseEntity<?>  teamByName = footBallTeamController.byTeamName("ManUnited");
		Assert.assertNotNull(teamByName);
		Assert.assertEquals(footBallTeamRepository.findByTeamName("ManUnited"),getContentFromResponseEntity((ResponseEntity<Resource<FootBallTeam>>) teamByName));
	}

	private FootBallTeam getContentFromResponseEntity(ResponseEntity<Resource<FootBallTeam>> responseEntity){
		Resource<FootBallTeam> footBallTeamResource =responseEntity.getBody();
		return footBallTeamResource.getContent();
	}

	@Test
	public void onTestFindAll(){

		Resources<Resource<FootBallTeam>> listAllTeams = footBallTeamController.listAllTeams();
		Assert.assertNotNull(listAllTeams);
		Collection<Resource<FootBallTeam>> footBallTeResourceCollection =listAllTeams.getContent();
		List<FootBallTeam> footBallTeamList = (List<FootBallTeam>) footBallTeamRepository.findAll();
		Assert.assertEquals(footBallTeamList.size(),footBallTeResourceCollection.size());
	}

	@Test
	public void onTestCreateTeam() throws FootBallTeamDataException {

		FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiantsTest").
				setCompetition("UFEA")
				.setCity("Belfast")
				.setNoOfPlayers(30)
				.setOwner("BelfastOWner")
				.setStadiumCapacity(Long.valueOf(30000))
				.build();

		ResponseEntity<Resource<FootBallTeam>>  footBallTeamCreatedResponse = footBallTeamController.createFootBallTeam(footBallTeam);
		Assert.assertNotNull(footBallTeamCreatedResponse);

		FootBallTeam createdTeam = getContentFromResponseEntity(footBallTeamCreatedResponse);
		Assert.assertEquals(createdTeam,footBallTeamRepository.findByTeamName("BelfastGiantsTest"));
	}

	@Test
	public void onTestUpdateTeam() throws FootBallTeamDataException {

		FootBallTeam footBallTeam = FootBallTeam.builder().setTeamName("BelfastGiants").
				setCompetition("UFEA")
				.setCity("Belfast")
				.setNoOfPlayers(30)
				.setOwner("BelfastOWnerTest")
				.setStadiumCapacity(Long.valueOf(30000))
				.build();

		ResponseEntity<?>  footBallTeamResource =  footBallTeamController.
																		updateTeam("BelfastGiants",footBallTeam);
		Assert.assertNotNull(footBallTeamResource);

		FootBallTeam updatedTeam = getContentFromResponseEntity((ResponseEntity<Resource<FootBallTeam>>) footBallTeamResource);
		Assert.assertEquals(updatedTeam,footBallTeamRepository.findByTeamName("BelfastGiants"));
	}

	@Test
	public void onTestDeleteByTeamName(){

		ResponseEntity<?>  deletedTeam = footBallTeamController.deleteTeam("ManUnited");
		Assert.assertNotNull(deletedTeam);

		ResponseEntity<CustomMessageType>  team = (ResponseEntity<CustomMessageType>) footBallTeamController.byTeamName("ManUnited");
		CustomMessageType customMessageType = team.getBody();
		Assert.assertNotNull(customMessageType);
	}
}
