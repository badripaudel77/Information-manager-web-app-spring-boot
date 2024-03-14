package info.keeper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InfoKeeperApplicationTests {

	@Test
	void contextLoads() {
		assert 1 == 1;
		assert "Test".equalsIgnoreCase("test");
		assert true != false;
	}

}
