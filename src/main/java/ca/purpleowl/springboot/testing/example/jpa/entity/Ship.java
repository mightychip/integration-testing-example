package ca.purpleowl.springboot.testing.example.jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ships")
public class Ship {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long shipId;

    @Column(name = "ship_name")
    private String shipName;

    @Column(name = "ship_type")
    private ShipType shipType;

    @ManyToOne
    @JoinColumn(name = "fleetId")
    private Fleet fleet;

    public Long getShipId() {
        return shipId;
    }

    public Ship setShipId(Long shipId) {
        this.shipId = shipId;
        return this;
    }

    public String getShipName() {
        return shipName;
    }

    public Ship setShipName(String shipName) {
        this.shipName = shipName;
        return this;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Ship setShipType(ShipType shipType) {
        this.shipType = shipType;
        return this;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public Ship setFleet(Fleet fleet) {
        this.fleet = fleet;
        return this;
    }

    public enum ShipType {
        SLOOP("Sloop"),
        GALLEON("Galleon");

        private String typeName;

        ShipType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }
}
