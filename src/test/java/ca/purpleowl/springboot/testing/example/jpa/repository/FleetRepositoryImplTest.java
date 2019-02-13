package ca.purpleowl.springboot.testing.example.jpa.repository;

import ca.purpleowl.springboot.testing.example.jpa.entity.Fleet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class FleetRepositoryImplTest {
    @PersistenceContext
    private EntityManager entityManager;

    private FleetRepositoryImpl fixture;

    @Before
    public void setup() {
        fixture = new FleetRepositoryImpl();
        fixture.setEntityManager(entityManager);
    }

    @Test
    public void testListSimilarFleetNames() {
        Fleet fleet1 = new Fleet().setFleetName("Test Fleet");
        Fleet fleet2 = new Fleet().setFleetName("Test Thingy");
        Fleet fleet3 = new Fleet().setFleetName("Fleet of Test");
        Fleet fleet4 = new Fleet().setFleetName("Bananabananabanana");
        entityManager.persist(fleet1);
        entityManager.persist(fleet2);
        entityManager.persist(fleet3);
        entityManager.persist(fleet4);
        entityManager.flush();

        List<String> results = fixture.listSimilarFleetNames("%Test%");

        assertNotNull(results);
        assertEquals(3, results.size());
        assertTrue(results.contains("Test Fleet"));
        assertTrue(results.contains("Test Thingy"));
        assertTrue(results.contains("Fleet of Test"));
    }
}
