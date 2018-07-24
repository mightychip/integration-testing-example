package ca.purpleowl.springboot.testing.example.jpa.repository;

import ca.purpleowl.springboot.testing.example.jpa.entity.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetRepository extends JpaRepository<Fleet, Long>, FleetRepositoryCustom {
}
