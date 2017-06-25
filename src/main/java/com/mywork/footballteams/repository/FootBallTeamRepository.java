package com.mywork.footballteams.repository;

import com.mywork.footballteams.model.FootBallTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Rest Data Repository that will expose the data
 * Switching off this exposure as we need to have a control
 * over that data that we expose to the client
 * Created by prabeenjayaraj on 23/06/2017.
 */

@RepositoryRestResource(exported = false    )
public interface FootBallTeamRepository extends CrudRepository<FootBallTeam,Long> {

    /**
     * This method will fetch the team based on the team name
     * @param teamName
     * @return
     */
    FootBallTeam findByTeamName(@Param("name") String teamName);

//    void delete(@Param("name") String teamName);
    /**
     * This will return all the football team data sorting based on the stadium capacity
     * This was explicitly created eventhough we can use the paging and sorting repository
     * @return
     */
    Iterable<FootBallTeam> findAllByOrderByStadiumCapacityDesc();
}