package info.keeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfoKeeperApplication {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(InfoKeeperApplication.class);
		logger.debug(">>application starting in localhost[ Triggering Jenkins Build]");
		SpringApplication.run(InfoKeeperApplication.class, args);
		logger.debug("<<application started fine in localhost [without docker] [Triggering Jenkins Build]");
	}
}
