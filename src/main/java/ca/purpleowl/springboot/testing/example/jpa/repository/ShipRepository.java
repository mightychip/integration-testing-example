package ca.purpleowl.springboot.testing.example.jpa.repository;

import ca.purpleowl.springboot.testing.example.jpa.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {
}
