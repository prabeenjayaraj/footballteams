package com.mywork.footballteams.services;

import com.mywork.footballteams.exception.FootBallTeamDataException;
import com.mywork.footballteams.model.FootBallTeam;
import com.mywork.footballteams.repository.FootBallTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by prabeenjayaraj on 21/06/2017.
 */

@Service
public class FootBallTeamService {

    private FootBallTeamRepository footBallTeamRepository;

    @Autowired
    public FootBallTeamService(FootBallTeamRepository footBallTeamRepository) {
        this.footBallTeamRepository = footBallTeamRepository;
    }

    public FootBallTeamRepository getFootBallTeamRepository() {
        return footBallTeamRepository;
    }

    public void setFootBallTeamRepository(FootBallTeamRepository footBallTeamRepository) {
        this.footBallTeamRepository = footBallTeamRepository;
    }

    public List<FootBallTeam> findAll(){
        return (List<FootBallTeam>) footBallTeamRepository.findAll();
    }

    public List<FootBallTeam> findAllOrderByStadiumCapcityDesc(){
        return (List<FootBallTeam>) footBallTeamRepository.findAllByOrderByStadiumCapacityDesc();
    }

    public void deleteByTeamId(Long id){
        footBallTeamRepository.delete(id);
    }

    public void deleteAllTeams(){
        footBallTeamRepository.deleteAll();
    }
    public FootBallTeam findByTeamName(String teamName){
        return footBallTeamRepository.findByTeamName(teamName);
    }

    public Boolean isTeamExist(String teamName){

        if(footBallTeamRepository.findByTeamName(teamName) !=null)
            return true;

        return false;
    }

    /**
     * Create  the FootBall Team by passing the footballteam entity
     * @param footBallTeam
     */
    public void saveFootBallTeam(FootBallTeam footBallTeam){
         footBallTeamRepository.save(footBallTeam);
    }

    /**
     * Create the FootBallTeam by passing the individual fields of the team
     * @param teamName
     * @param city
     * @param owner
     * @param stadiumCapacity
     * @param competition
     * @param noOfPlayers
     * @throws FootBallTeamDataException
     */
    public  void createFootBallTeam(String teamName,String city,String owner,Long stadiumCapacity,
                                    String competition,Integer noOfPlayers ) throws FootBallTeamDataException{

              FootBallTeam footBallTeam = FootBallTeam.builder().setCity(city)
                                .setTeamName(teamName).setOwner(owner).setStadiumCapacity(stadiumCapacity)
                                .setCompetition(competition)
                                .setNoOfPlayers(noOfPlayers)
                                .build();


                saveFootBallTeam(footBallTeam);
    }


}
