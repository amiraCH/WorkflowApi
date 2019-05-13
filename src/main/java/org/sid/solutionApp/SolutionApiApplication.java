package org.sid.solutionApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SolutionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolutionApiApplication.class, args);
	}

}
