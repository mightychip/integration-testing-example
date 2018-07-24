package ca.purpleowl.springboot.testing.example.jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fleets")
public class Fleet {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long fleetId;

    @Column(name = "fleet_name")
    private String fleetName;

    @OneToMany
    private List<Ship> ships = new ArrayList<>();

    public Long getFleetId() {
        return fleetId;
    }

    public Fleet setFleetId(Long fleetId) {
        this.fleetId = fleetId;
        return this;
    }

    public String getFleetName() {
        return fleetName;
    }

    public Fleet setFleetName(String fleetName) {
        this.fleetName = fleetName;
        return this;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public Fleet setShips(List<Ship> ships) {
        this.ships = ships;
        return this;
    }
}
