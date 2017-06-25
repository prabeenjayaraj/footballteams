package com.mywork.footballteams.model;

import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

/**
 * This Resoource will be the data that will be exposed to the client
 * as we dont want to have a control over the data that we expose
 * and also we dont want to show the raw data to the client
 * Created by prabeenjayaraj on 23/06/2017.
 */
public class FootBallTeamResource extends ResourceSupport {

    private String teamName;
    private String city;
    private String owner;
    private Long stadiumCapacity;
    private String competition;
    private Integer noOfPlayers=0;
    private LocalDate creationDate ;

    public FootBallTeamResource(FootBallTeam footBallTeam){
        teamName =footBallTeam.getTeamName();
        city= footBallTeam.getCity();
        owner=footBallTeam.getOwner();
        competition =footBallTeam.getCompetition();
        stadiumCapacity=footBallTeam.getStadiumCapacity();
        noOfPlayers=footBallTeam.getNoOfPlayers();
        creationDate=footBallTeam.getCreationDate();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getStadiumCapacity() {
        return stadiumCapacity;
    }

    public void setStadiumCapacity(Long stadiumCapacity) {
        this.stadiumCapacity = stadiumCapacity;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public Integer getNoOfPlayers() {
        return noOfPlayers;
    }

    public void setNoOfPlayers(Integer noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
