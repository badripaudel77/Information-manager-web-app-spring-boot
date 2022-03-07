package info.keeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfoKeeperApplication {
	public static void main(String[] args) {
		System.out.println(">>application starting in docker but with local postgreSQL database");
		SpringApplication.run(InfoKeeperApplication.class, args);
		System.out.println("<<application started fine with docker but with local postgreSQL database");
	}
}
