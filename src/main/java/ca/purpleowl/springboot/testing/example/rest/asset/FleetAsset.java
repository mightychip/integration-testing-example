package ca.purpleowl.springboot.testing.example.rest.asset;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

//TODO hmmmm... maybe make these final classes instead.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FleetAsset {
    private Long fleetId;
    private String fleetName;

    public Long getFleetId() {
        return fleetId;
    }

    public FleetAsset setFleetId(Long fleetId) {
        this.fleetId = fleetId;
        return this;
    }

    public String getFleetName() {
        return fleetName;
    }

    public FleetAsset setFleetName(String fleetName) {
        this.fleetName = fleetName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FleetAsset)) return false;

        FleetAsset asset = (FleetAsset) o;

        return new EqualsBuilder()
                .append(getFleetId(), asset.getFleetId())
                .append(getFleetName(), asset.getFleetName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getFleetId())
                .append(getFleetName())
                .toHashCode();
    }
}
