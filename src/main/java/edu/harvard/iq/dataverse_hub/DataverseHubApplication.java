package edu.harvard.iq.dataverse_hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataverseHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataverseHubApplication.class, args);
	}
}
