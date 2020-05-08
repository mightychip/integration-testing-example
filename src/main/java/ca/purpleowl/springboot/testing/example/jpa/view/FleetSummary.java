package ca.purpleowl.springboot.testing.example.jpa.view;

public class FleetSummary {
    private final String fleetName;
    private final long fleetSize;

    public FleetSummary(String fleetName, long fleetSize) {
        this.fleetName = fleetName;
        this.fleetSize = fleetSize;
    }

    public String getFleetName() {
        return fleetName;
    }

    public long getFleetSize() {
        return fleetSize;
    }
}
