package com.mywork.footballteams;

import com.mywork.footballteams.services.FootBallTeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FootBallTeamsApplication {

	private  Logger logger = LogManager.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		SpringApplication.run(FootBallTeamsApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(FootBallTeamService footBallTeamService){

		return args->{

			logger.info("Initialize the application with Team Data ");
			footBallTeamService.createFootBallTeam("ManUnited","Manchester","ManJoe",Long.valueOf(1000),"UFEA",11);
			footBallTeamService.createFootBallTeam("Chelsea","ChelseaCity","ChelseaOwner",Long.valueOf(2000),"UFEA",20);
			footBallTeamService.createFootBallTeam("BelfastGiants","BelfastCity","BelfastOwner",Long.valueOf(20056),"UFEA",23);
			footBallTeamService.createFootBallTeam("LiverPool","LiverPoolCity","LiverPoolOwner",Long.valueOf(30056),"UFEA",13);

			logger.info("End of Data initialization ");

			footBallTeamService.findAll()
					.forEach(  aa->logger.info(aa.toString()));
		}



		;
	}
}
