package ca.purpleowl.springboot.testing.example.jpa.repository;

import ca.purpleowl.springboot.testing.example.jpa.entity.Fleet;
import ca.purpleowl.springboot.testing.example.jpa.view.FleetSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FleetRepository extends JpaRepository<Fleet, Long>, FleetRepositoryCustom {
    @Query("SELECT new ca.purpleowl.springboot.testing.example.jpa.view.FleetSummary(f.fleetName, COUNT(s)) FROM Fleet f INNER JOIN f.ships s GROUP BY f.fleetName")
    List<FleetSummary> summarizeFleets();
}
