package musiquest;

//import java.sql.SQLException;

import musiquest.SoloUser.SoloUser;
import musiquest.SoloUser.SoloUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EnableJpaRepositories
public class MusiQuestApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(MusiQuestApplication.class, args);
	}

//	@Bean
//	CommandLineRunner initUser(SoloUserRepository userRepository) {
//		return args -> {
//			SoloUser user1 = new SoloUser("John", "john@somemail.com");
//			SoloUser user2 = new SoloUser("Jane", "jane@somemail.com");
//			SoloUser user3 = new SoloUser("Justin", "justin@somemail.com");
//
//
//		};


	}
