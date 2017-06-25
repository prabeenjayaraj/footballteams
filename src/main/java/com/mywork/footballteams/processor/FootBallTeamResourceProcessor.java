package com.mywork.footballteams.processor;

import com.mywork.footballteams.controller.FootBallTeamController;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Adding the footBallTeams resource link to the root resource
 * so that it can be seen at the root
 * Created by prabeenjayaraj on 24/06/2017.
 */

@Component
public class FootBallTeamResourceProcessor implements ResourceProcessor<RepositoryLinksResource> {

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(linkTo(methodOn(FootBallTeamController.class).
                listAllTeams()).withSelfRel());
        return resource;
    }
}
