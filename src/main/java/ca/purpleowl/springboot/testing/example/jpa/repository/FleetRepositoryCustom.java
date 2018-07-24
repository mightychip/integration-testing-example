package ca.purpleowl.springboot.testing.example.jpa.repository;

import java.util.List;

public interface FleetRepositoryCustom {
    List<String> listSimilarFleetNames(String similarTo);
}
