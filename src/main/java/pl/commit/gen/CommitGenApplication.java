package pl.commit.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pl.commit"})
public class CommitGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitGenApplication.class, args);
	}

}
