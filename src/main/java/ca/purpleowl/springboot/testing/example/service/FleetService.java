package ca.purpleowl.springboot.testing.example.service;

import ca.purpleowl.springboot.testing.example.jpa.entity.Fleet;
import ca.purpleowl.springboot.testing.example.jpa.repository.FleetRepository;
import ca.purpleowl.springboot.testing.example.rest.asset.FleetAsset;
import ca.purpleowl.springboot.testing.example.rest.asset.FleetListAsset;
import ca.purpleowl.springboot.testing.example.rest.asset.serializer.AssetSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FleetService {
    private static final Logger logger = LoggerFactory.getLogger(FleetService.class);

    private final FleetRepository fleetRepository;
    private final AssetSerializer assetSerializer;

    @Autowired
    public FleetService(FleetRepository fleetRepository) {
        this.fleetRepository = fleetRepository;

        this.assetSerializer = new AssetSerializer();
    }

    public FleetAsset findById(Long fleetId) {
        Fleet fleet = fleetRepository.findOne(fleetId);
        return assetSerializer.entityToAsset(fleet);
    }

    public List<FleetAsset> findAll() {
        //TODO maybe want to allow for some kind of error here...
        return fleetRepository.findAll()
                              .stream()
                              .map(assetSerializer::entityToAsset)
                              .collect(Collectors.toList());
    }

    //TODO Add order parameters and a few other nice-to-haves.
    public FleetListAsset listFleets(int pageSize, int page) {
        //TODO Actually add paging... currently not doing that.
        List<Fleet> fleets = fleetRepository.findAll();
        return new FleetListAsset()
                .setCount(fleets.size())
                .setPage(0)//TODO This should be a real page number instead.
                .setFleets(fleets.stream().map(assetSerializer::entityToAsset).collect(Collectors.toList()));
    }

    public Long saveFleet(FleetAsset asset) {
        //FIXME technically if it has an ID, we should be reading it and updating the entity, right???
        Fleet fleet = assetSerializer.assetToEntity(asset);
        fleet = fleetRepository.save(fleet);
        return fleet.getFleetId();
    }

    public List<String> fleetNamesSimilarTo(String similarTo) {
        return fleetRepository.listSimilarFleetNames(similarTo);
    }
}
