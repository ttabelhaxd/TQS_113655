package tqs.lab6_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "tqs.lab6_1.repository")
public class Lab61Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab61Application.class, args);
    }

}
