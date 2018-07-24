package ca.purpleowl.springboot.testing.example.rest.asset.serializer;

import ca.purpleowl.springboot.testing.example.jpa.entity.Fleet;
import ca.purpleowl.springboot.testing.example.rest.asset.FleetAsset;

/**
 * TODO This may end up being best as a component, so that it can be easily injected where needed...
 */
public class AssetSerializer {
    public Fleet assetToEntity(FleetAsset asset) {
        return new Fleet()
                .setFleetId(asset.getFleetId())
                .setFleetName(asset.getFleetName());
    }

    public FleetAsset entityToAsset(Fleet entity) {
        return new FleetAsset()
                .setFleetId(entity.getFleetId())
                .setFleetName(entity.getFleetName());
    }
}
