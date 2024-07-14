package globalquake.core.station;

import java.util.Collection;

public class NearbyStationManager {
    private Collection<NearbyStationDistanceInfo> nearbyStations;

    public void setNearbyStations(Collection<NearbyStationDistanceInfo> nearbyStations) {
        this.nearbyStations = nearbyStations;
    }

    public Collection<NearbyStationDistanceInfo> getNearbyStations() {
        return nearbyStations;
    }

    public void clear() {
        nearbyStations.clear();
    }
}

