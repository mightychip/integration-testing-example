package ca.purpleowl.springboot.testing.example.jpa.repository;

import ca.purpleowl.springboot.testing.example.jpa.entity.Fleet;
import ca.purpleowl.springboot.testing.example.jpa.entity.Ship;
import ca.purpleowl.springboot.testing.example.jpa.view.FleetSummary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class FleetRepositoryTest {
    @Autowired
    private FleetRepository fixture;

    @Test
    public void testSummarizeFleets() {
        List<Ship> fleet1Ships = new ArrayList<>();
        fleet1Ships.add(
            new Ship()
                .setShipName("Ship 1")
                .setShipType(Ship.ShipType.SLOOP)
        );
        fleet1Ships.add(
            new Ship()
                .setShipName("Ship 2")
                .setShipType(Ship.ShipType.SLOOP)
        );
        fleet1Ships.add(
            new Ship()
                .setShipName("Ship 3")
                .setShipType(Ship.ShipType.GALLEON)
        );
        Fleet fleet1 = new Fleet()
                .setFleetName("Fleet 1")
                .setShips(fleet1Ships);

        fixture.saveAndFlush(fleet1);

        List<Ship> fleet2Ships = new ArrayList<>();
        fleet2Ships.add(
            new Ship()
                .setShipName("Ship 1")
                .setShipType(Ship.ShipType.SLOOP)
        );
        fleet2Ships.add(
            new Ship()
                .setShipName("Ship 2")
                .setShipType(Ship.ShipType.SLOOP)
        );
        fleet2Ships.add(
            new Ship()
                .setShipName("Ship 3")
                .setShipType(Ship.ShipType.GALLEON)
        );
        Fleet fleet2 = new Fleet()
                .setFleetName("Fleet 2")
                .setShips(fleet2Ships);

        fixture.saveAndFlush(fleet2);

        Optional<Fleet> test = fixture.findById(fleet1.getFleetId());

        assertTrue(test.isPresent());
        assertEquals(3, test.get().getShips().size());
        // TODO Everything up to here could be avoided by using an SQL import for this test... should look into that.

        List<FleetSummary> result = fixture.summarizeFleets();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
