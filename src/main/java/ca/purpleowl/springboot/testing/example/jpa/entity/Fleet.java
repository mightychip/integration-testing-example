package ca.purpleowl.springboot.testing.example.jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "fleets")
public class Fleet {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long fleetId;

    @Column(name = "fleet_name")
    private String fleetName;

    @OneToMany(mappedBy = "fleet", cascade = ALL)
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
        ships.forEach(ship -> ship.setFleet(this));
        this.ships.addAll(ships);
        return this;
    }
}
