package info.keeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfoKeeperApplication {
	public static void main(String[] args) {
		System.out.println(">>application starting with docker-compose");
		SpringApplication.run(InfoKeeperApplication.class, args);
		System.out.println("<<application started with docker-compose fine");
	}
}
