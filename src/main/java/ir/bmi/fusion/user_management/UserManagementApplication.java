package ir.bmi.fusion.user_management;

import ir.bmi.fusion.user_management.infrastructure.config.VaultPropertySourceRegisterer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		// fluent API to build an ApplicationContext hierarchy, customize environment and context
		new SpringApplicationBuilder(UserManagementApplication.class)
				//.initializers(new VaultPropertySourceRegisterer())
				.run(args);
	}

}
