package ca.purpleowl.springboot.testing.example.rest.asset;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class FleetListAsset {
    private int page;
    private int count;
    private List<FleetAsset> fleets;

    public int getPage() {
        return page;
    }

    public FleetListAsset setPage(int page) {
        this.page = page;
        return this;
    }

    public int getCount() {
        return count;
    }

    public FleetListAsset setCount(int count) {
        this.count = count;
        return this;
    }

    public List<FleetAsset> getFleets() {
        return fleets;
    }

    public FleetListAsset setFleets(List<FleetAsset> fleets) {
        this.fleets = fleets;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FleetListAsset)) return false;

        FleetListAsset that = (FleetListAsset) o;

        return new EqualsBuilder()
                .append(getPage(), that.getPage())
                .append(getCount(), that.getCount())
                .append(getFleets(), that.getFleets())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPage())
                .append(getCount())
                .append(getFleets())
                .toHashCode();
    }
}
