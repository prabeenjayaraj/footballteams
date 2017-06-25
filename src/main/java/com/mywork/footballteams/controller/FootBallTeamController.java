package com.mywork.footballteams.controller;

import com.mywork.footballteams.exception.CustomMessageType;
import com.mywork.footballteams.model.FootBallTeam;
import com.mywork.footballteams.services.FootBallTeamService;
import com.mywork.footballteams.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * RestController that will provide the methods for faciliating
 * the client interaction
 * Created by prabeenjayaraj on 22/06/2017.
 */

@RestController
public class FootBallTeamController {

    @Autowired
    private FootBallTeamService footBallTeamService;

    private Logger logger = LogManager.getLogger(this.getClass().getName());

    /**
     * Method to retrieve all the FootBall Teams
     * Also add the Links to resource for client navigation
     * @return
     */
    @RequestMapping(value = "/footBallTeams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Resource<FootBallTeam>> listAllTeams() {
        logger.info("Start Retrieving all the footballteams ");
        List<Resource<FootBallTeam>> resourceList = footBallTeamService.findAll()
                .stream()
                .map(team -> getFootBallTeamResource(team))
                .collect(Collectors.toList());

        logger.info("Adding the Links to the List of footballteams ");
        Link listByOrderLink = linkTo(methodOn(FootBallTeamController.class).listAllTeamsOrderByStadiumsCapacityDesc()).withSelfRel();
        Resources<Resource<FootBallTeam>> resources = new Resources<Resource<FootBallTeam>>(resourceList, listByOrderLink);
        logger.info("Returning the List Of teams retrieved");
        return resources;
    }

    /**
     * Method the search the teams based on the name
     * Also add the links to the resource for client navigation
     * Throws a Resource not Found exception if the football team is not present in the system
     * @param teamName
     * @return
     */
    @RequestMapping(value = "/footBallTeams/{teamName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> byTeamName(@PathVariable("teamName") String teamName) {
        logger.info("Retrieving the footballteams based on the name= " + teamName);
        FootBallTeam footBallTeam= footBallTeamService.findByTeamName(teamName);
        if(footBallTeam==null){
            return new ResponseEntity(new CustomMessageType("FootBallTeam with name: " + teamName + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        Resource<FootBallTeam> resource = new Resource<FootBallTeam>(footBallTeam);
        resource.add(linkTo(methodOn(FootBallTeamController.class).
                listAllTeams()).withRel("footBallTeams"));

       return new ResponseEntity<Resource<FootBallTeam>>(resource,HttpStatus.OK);
    }

    /**
     * Method to retrieve the football teams ordered by the
     * statium capacity in Desc order
     * @return
     */
    @RequestMapping(value = "/footBallTeams/search/findAllOrderByStadiumCapcityDesc/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Resource<FootBallTeam>> listAllTeamsOrderByStadiumsCapacityDesc() {

        Link listAllLink = linkTo(methodOn(FootBallTeamController.class).listAllTeams()).withSelfRel();
        List<Resource<FootBallTeam>> resourceList = footBallTeamService.findAllOrderByStadiumCapcityDesc().stream()
                .map(team -> getFootBallTeamResource(team))
                .collect(Collectors.toList());

        Resources<Resource<FootBallTeam>> resources  = new Resources<Resource<FootBallTeam>>(resourceList, listAllLink);
        return resources;

    }

    /**
     * Method to create the FootBallTeam Resource by adding the links for naviagtion also
     * @param footBallTeam
     * @return
     */

    private Resource<FootBallTeam> getFootBallTeamResource(FootBallTeam footBallTeam) {
        Resource<FootBallTeam> resource = new Resource<FootBallTeam>(footBallTeam);
        resource.add(linkTo(methodOn(FootBallTeamController.class).byTeamName(footBallTeam.getTeamName())).withSelfRel());
        resource.add(linkTo(methodOn(FootBallTeamController.class).
                listAllTeams()).withRel("footBallTeams"));
        return resource;
    }


    /**
     * Method to DELETE the team based on the name
     *
     * Throws a Resource not Found exception if the football team is not present in the system
     * @param teamName
     * @return
     */
    @RequestMapping(value = "/footBallTeams/{teamName}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTeam(@PathVariable("teamName") String teamName) {
        logger.info("Deleting the footballteam based on the name= " + teamName);
        FootBallTeam footBallTeam= footBallTeamService.findByTeamName(teamName);
        if(footBallTeam==null) {
            logger.info("Unable to Delete Team with name "+ teamName);
            return new ResponseEntity(new CustomMessageType("Unable to delete. Team with name: " + teamName + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        footBallTeamService.deleteByTeamId(footBallTeam.getId());
        return new ResponseEntity<FootBallTeam>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/footBallTeams", method = RequestMethod.DELETE)
    public ResponseEntity<FootBallTeam> deleteAllTeams() {
        logger.info("Deleting All FootBallTeams");
        footBallTeamService.deleteAllTeams();
        return new ResponseEntity<FootBallTeam>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/footBallTeams", method = RequestMethod.POST)
    public ResponseEntity<Resource<FootBallTeam>> createFootBallTeam(@RequestBody FootBallTeam footBallTeam) {
        logger.info("Creating FootBallTeam : {}", footBallTeam);

        if (footBallTeamService.isTeamExist(footBallTeam.getTeamName())) {
            logger.error("Unable to create footBallTeam " + footBallTeam.getTeamName() );
            return new ResponseEntity(new CustomMessageType("Unable to create. A FootBallTeam with name " +
                    footBallTeam.getTeamName() + " already exist."), HttpStatus.CONFLICT);
        }
        footBallTeamService.saveFootBallTeam(footBallTeam);
        FootBallTeam createdFootBallTeam = footBallTeamService.findByTeamName(footBallTeam.getTeamName());

        if(createdFootBallTeam==null){
            logger.error("Unable to create footBallTeam " + footBallTeam.getTeamName() + "Due to unknown issue");
            return new ResponseEntity(new CustomMessageType("Unable to create. A FootBallTeam with name " +
                    footBallTeam.getTeamName() + " , Unkown Issue"), HttpStatus.EXPECTATION_FAILED);
        }

        Resource<FootBallTeam> footBallTeamResource = getFootBallTeamResource(createdFootBallTeam);
        return new ResponseEntity<Resource<FootBallTeam>>(footBallTeamResource, HttpStatus.OK);
    }



    @RequestMapping(value = "/footBallTeams/{teamName}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTeam(@PathVariable("teamName") String teamName, @RequestBody FootBallTeam footBallTeam) {
        logger.info("Updating the Team " + teamName);

        FootBallTeam exitingFootBallTeam = footBallTeamService.findByTeamName(teamName);

        if (exitingFootBallTeam == null) {
            logger.error("FootBallteam " +teamName + " Not Found , hence update failed");
            return new ResponseEntity(new CustomMessageType("Unable to Update FootBall Team " + teamName + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        FootBallTeam updatedFootBallTeam = copyFootBallTeamProperties(footBallTeam,exitingFootBallTeam);

        footBallTeamService.saveFootBallTeam(updatedFootBallTeam);

        Resource<FootBallTeam> footBallTeamResource = getFootBallTeamResource(updatedFootBallTeam);
        return new ResponseEntity<Resource<FootBallTeam>>(footBallTeamResource, HttpStatus.OK);
    }

    /**
     * Copying the incoming data onto the existing data
     * Returning the object just to keep the readability better
     * @param orig
     * @param dest
     * @return
     */
    private FootBallTeam copyFootBallTeamProperties(FootBallTeam orig, FootBallTeam dest){

        BeanUtilsBean copyNonNullFields =new NullAwareBeanUtilsBean();
        try {
            copyNonNullFields.copyProperties(dest, orig);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return dest;

    }
}
