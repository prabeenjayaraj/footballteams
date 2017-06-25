
 RESTFUL web service for football teams that can be accessed via http.

• It should be possible to post to create a team with the following properties:

o Name

o City

o Owner

o Stadium Capacity

o Competition

o Number of Players

o Date of Creation

• Get a list of all Teams in JSON format

• Get a list of teams sorted by their stadium capacity.

• Get the details of a specific Team in JSON format

• The app should include logging.

• The app should contain some form of unit testing.



Frameworks Used
The API is created using Spring Data , JPA, REST , Hateos, JSON Logging, H2 database and also have added HAL browser support

Below are the API features created for this service

    1.Create football teams with its attributes
    2.Update FootBallTeam data
    3.Retrieve team data
    4. List all foot ball teams
    5. List all teams sorted by stadium capacity
    6. Delete foot ball team by name
    7. Delete All Football Teams


1. The FootBallTeam with the below attributes are created in H2 in memory database.
2. The Data is persisted using Spring data JPA as entities
3. Spring Data REST feature is switched off , so that it gives us more control
    as to what data we need to expose to the client , rather than exposing the repository
4. Spring REST controller is created to expose the API to the client
5. Hateos Links are added to the Resources to have the highest level of client navigation and exposure
6. Logging is purposefully done in JSON format so that when this gets deployed in Docker container
    the logging data can be directly sent to logstash without any additional conversion needed
    in logstash to store this data in Elastic Search
6. HAL browser support is added for easy navigation Help
7. Unit Tests were run using code coverage