package ca.purpleowl.springboot.testing.example.integration;

import ca.purpleowl.springboot.testing.example.jpa.entity.Fleet;
import ca.purpleowl.springboot.testing.example.jpa.repository.FleetRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * FIXME - Might be a good idea to figure out a different name so that you can use it in a wildcard for Surefire.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FleetE2EIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FleetRepository fleetRepository;

    @After
    public void cleanup() {
        fleetRepository.deleteAll();
    }

    @Test
    public void testReadSimpleFleet() {
        Fleet fleet = new Fleet().setFleetName("Test Fleet");
        fleetRepository.save(fleet);
        RequestEntity<Void> request = RequestEntity
                .get(URI.create("/fleets"))
                .header("Content-Type", "application/json")
                .build();

        //Call the service.  We don't need to know about the port or where it's mounted.  The TestRestTemplate takes
        //care of that for us.  This is the best kind of automagic.
        ResponseEntity<String> result = restTemplate.exchange("/fleets", HttpMethod.GET, request, String.class);

        //TODO This should come from a file instead.
        String expectedJson = "{\"page\":0,\"count\":1,\"fleets\":[{\"fleetId\":1,\"fleetName\":\"Test Fleet\"}]}";

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJson, result.getBody());
    }

    @Test
    public void testListFleetNamesSimilarTo() {
        Fleet fleet = new Fleet().setFleetName("Test Fleet");
        fleetRepository.save(fleet);
        RequestEntity<Void> request = RequestEntity
                .get(URI.create("/fleets-like"))
                .header("Content-Type", "application/json")
                .build();

        ResponseEntity<String> result = restTemplate.exchange("/fleets-like?similarTo={similarTo}", HttpMethod.GET, request, String.class, Collections.singletonMap("similarTo", "Test%"));

        //In most cases, this would come from a file, but this is pretty elementary, my dear Watson.
        String expectedJson = "[\"Test Fleet\"]";

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJson, result.getBody());
    }
}
