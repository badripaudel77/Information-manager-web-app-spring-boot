package info.keeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfoKeeperApplication {
	static Logger logger = LoggerFactory.getLogger(InfoKeeperApplication.class);
	public static void main(String[] args) {
		logger.info("Information app is running ");
		SpringApplication.run(InfoKeeperApplication.class, args);
	}
}
