package ir.bmi.fusion.user_management.integration;

import ir.bmi.fusion.user_management.domain.port.outbound.KeyProviderPort;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ContextConfiguration(classes = EmbeddedDatabaseConfiguration.class)
@ActiveProfiles("test")
class UserManagementApplicationTests {
	@MockitoBean
	KeyProviderPort keyProviderPort;

	@Test
	void contextLoads() {
	}

}
