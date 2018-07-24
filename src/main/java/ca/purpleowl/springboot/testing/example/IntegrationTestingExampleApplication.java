package ca.purpleowl.springboot.testing.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * As soon as we start adding extra pieces, we should consider creating @Configuration
 * Beans rather than extending this class.
 *
 * TODO That's now happening so move the EnableJpaRepositories to another config class.
 */
@SpringBootApplication
@EnableJpaRepositories("ca.purpleowl.springboot.testing.example.jpa.repository")
public class IntegrationTestingExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(IntegrationTestingExampleApplication.class, args);
    }
}
