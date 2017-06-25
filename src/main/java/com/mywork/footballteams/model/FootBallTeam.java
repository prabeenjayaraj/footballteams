package com.mywork.footballteams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mywork.footballteams.exception.FootBallTeamDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The model Entity that will hold the data for persisting in the in memory H2 database
 *
 * Created by prabeenjayaraj on 23/06/2017.
 */

@Entity
public class FootBallTeam implements Serializable{

    @Id
    @GeneratedValue
    @JsonIgnore
     private Long id;
    /** Ignoring this property in the JSON resource as the client doesnt need to know about the internal ID of the
     * FootBallTeam
     */

    @Column
    private String teamName;

    @Column
    private String city;

    @Column
    private String owner;

    @Column
    private Long stadiumCapacity;

    @Column
    private String competition;

    @Column
    private Integer noOfPlayers=0;

    @Column
    private LocalDate creationDate = LocalDate.now();

    public FootBallTeam(String teamName, String city, String owner, Long stadiumCapacity,
                        String competition, Integer noOfPlayers) {
        this.teamName = teamName;
        this.city = city;
        this.owner = owner;
        this.stadiumCapacity = stadiumCapacity;
        this.competition = competition;
        this.noOfPlayers = noOfPlayers;
    }

    public FootBallTeam(){

    }

    public static FootBallTeamEntityBuilder builder(){
        return new FootBallTeamEntityBuilder();
    }
    public Long getId() {
        return id;
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

    public Integer  getNoOfPlayers() {
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

    @Override
    public String toString() {
        return "FootBallTeam{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", city='" + city + '\'' +
                ", owner='" + owner + '\'' +
                ", stadiumCapacity=" + stadiumCapacity +
                ", competition='" + competition + '\'' +
                ", noOfPlayers=" + noOfPlayers +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FootBallTeam that = (FootBallTeam) o;

        if (!id.equals(that.id)) return false;
        if (!teamName.equals(that.teamName)) return false;
        if (!city.equals(that.city)) return false;
        if (!owner.equals(that.owner)) return false;
        if (!stadiumCapacity.equals(that.stadiumCapacity)) return false;
        if (!competition.equals(that.competition)) return false;
        if (!noOfPlayers.equals(that.noOfPlayers)) return false;
        return creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + teamName.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + stadiumCapacity.hashCode();
        result = 31 * result + competition.hashCode();
        result = 31 * result + noOfPlayers.hashCode();
        result = 31 * result + creationDate.hashCode();
        return result;
    }

    /**
     * Builder to help in building the FootBallTeam Data
     * This will also perform validation on the data passed for creating the FootBallTeam object
     */
    public static class FootBallTeamEntityBuilder {


        private String teamName;
        private String city;
        private String owner;
        private Long stadiumCapacity= Long.valueOf(0);
        private String competition;
        private Integer noOfPlayers = 0;

        private Logger logger = LogManager.getLogger(this.getClass().getName());

        public FootBallTeamEntityBuilder setTeamName(String teamName){
            this.teamName= teamName;
            return this;
        }

        public FootBallTeamEntityBuilder setCity(String city){
            this.city= city;
            return this;
        }

        public FootBallTeamEntityBuilder setOwner(String owner){
            this.owner=owner;
            return this;
        }

        public FootBallTeamEntityBuilder setStadiumCapacity(Long stadiumCapacity){
            this.stadiumCapacity=stadiumCapacity;
            return this;
        }

        public FootBallTeamEntityBuilder setCompetition(String competition){
            this.competition = competition;

            return this;
        }

        public FootBallTeamEntityBuilder setNoOfPlayers(Integer noOfPlayers){
            this.noOfPlayers=noOfPlayers;
            return this;
        }

        public FootBallTeam build() throws FootBallTeamDataException{
            validate();// Validate the Data passed
            return new FootBallTeam(teamName,city,owner,stadiumCapacity,competition,noOfPlayers);
        }

        private void validate() throws FootBallTeamDataException{

            logger.info("Starting the Validation of the FootBallTeam Data");

            if(StringUtils.isEmpty(this.teamName))
                throw new FootBallTeamDataException("TeamName", "Is Null");

            if(StringUtils.isEmpty(this.city))
                throw new FootBallTeamDataException("City", "Is Null");

            if(StringUtils.isEmpty(this.owner))
                throw new FootBallTeamDataException("Owner", "Is Null");

            if(this.stadiumCapacity <100)
                throw new FootBallTeamDataException("Stadium Capcity", "Cant be Less than 100");

            if(StringUtils.isEmpty(this.competition))
                throw new FootBallTeamDataException("Competition", "Is Null ");

            if(noOfPlayers <10)
                throw new FootBallTeamDataException("NoOfPlayers", "Is Too Less To Create a Team");

            logger.info("End of the Validation of the FootBallTeam Data");

        }
    }

}
