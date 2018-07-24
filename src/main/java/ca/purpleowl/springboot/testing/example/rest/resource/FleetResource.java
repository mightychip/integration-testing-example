package ca.purpleowl.springboot.testing.example.rest.resource;

import ca.purpleowl.springboot.testing.example.rest.annotation.JSONGET;
import ca.purpleowl.springboot.testing.example.rest.asset.FleetAsset;
import ca.purpleowl.springboot.testing.example.rest.asset.FleetListAsset;
import ca.purpleowl.springboot.testing.example.service.FleetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FleetResource {
    private static final String FLEET_ID_PARAM = "fleetId";

    private static final String FLEETS_FOUND_MESSAGE = "Found %d fleets to return.";
    private static final String GET_BY_ID_INVALID_PARAMETER_MESSAGE = "Invalid parameter passed to getFleetById!  %s is not a valid numeric ID!";
    private static final String FLEET_NOT_FOUND_MESSAGE = "Fleet with ID %d not found.";

    private static final Logger logger = LoggerFactory.getLogger(FleetResource.class);

    private final FleetService fleetService;

    @Autowired
    public FleetResource(FleetService fleetService) {
        this.fleetService = fleetService;
    }



    @JSONGET(path = "/fleets")
    public ResponseEntity<FleetListAsset> getAllFleets() {
        FleetListAsset fleetList = fleetService.listFleets(0, 0);

        logger.info(String.format(FLEETS_FOUND_MESSAGE, fleetList.getFleets().size()));

        return ResponseEntity.ok()
                             .body(fleetList);
    }

    @JSONGET(path = "/fleets-like")
    public ResponseEntity<List<String>> getFleetsWithNamesLike(@RequestParam(value = "similarTo") String similarTo) {
        return ResponseEntity.ok()
                             .body(fleetService.fleetNamesSimilarTo(similarTo));


    }

    @JSONGET(path = "/fleet/{fleetId}")
    public ResponseEntity<FleetAsset> getFleetById(@PathVariable(name = FLEET_ID_PARAM) String fleetId) {
        Long id;

        try {
            id = Long.parseLong(fleetId);
        } catch (NumberFormatException e) {
            logger.warn(String.format(GET_BY_ID_INVALID_PARAMETER_MESSAGE, fleetId));
            return ResponseEntity.badRequest().build();
        }

        FleetAsset fleet = fleetService.findById(id);

        if(fleet == null) {
            logger.warn(String.format(FLEET_NOT_FOUND_MESSAGE, id));
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                             .body(fleet);
    }
}
