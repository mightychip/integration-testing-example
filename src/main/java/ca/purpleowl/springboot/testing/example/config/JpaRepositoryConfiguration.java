package ca.purpleowl.springboot.testing.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ca.purpleowl.springboot.testing.example.jpa.repository")
public class JpaRepositoryConfiguration {
}
