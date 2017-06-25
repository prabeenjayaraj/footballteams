Rest API Created for FootBallTeams
1.Create football teams with its attributes
2.Update FootBallTeam data
3.Retrieve team data
4. List all teams sorted by stadium capacity
5. Delete foot ball team




The API is created using Spring Data , JPA, REST , Hateos, H2 database and also have added HAL browser support

1. The FootBallTeam with the below attributes are created in H2 in memory database.
2. The Data is persisted using Spring data JPA as entities
3. Spring Data REST feature is switched off , so that it gives us more control
    as to what data we need to expose to the client , rather than exposing the repository
4. Spring REST controller is created to expose the API to the client
5.  Hateos Links are added to the Resources to have the highest level of client navigation and exposure
6. HAL browser support is added for easy navigation Help
7. Unit Tests were run using code coverage