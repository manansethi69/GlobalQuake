package globalquake.core.earthquake.quality;

import globalquake.core.earthquake.data.HypocenterFinderSettings;
import globalquake.core.earthquake.data.PickedEvent;

import java.util.List;

public class ScanAreaParameters {
    private List<PickedEvent> events;
    private double maxDist;
    private int points;
    private double lat;
    private double lon;
    private int depthIterations;
    private double maxDepth;
    private HypocenterFinderSettings finderSettings;

    public ScanAreaParameters(List<PickedEvent> events, double maxDist, int points, double lat, double lon, int depthIterations, double maxDepth, HypocenterFinderSettings finderSettings) {
        this.events = events;
        this.maxDist = maxDist;
        this.points = points;
        this.lat = lat;
        this.lon = lon;
        this.depthIterations = depthIterations;
        this.maxDepth = maxDepth;
        this.finderSettings = finderSettings;
    }

    // Getters for all fields
    public List<PickedEvent> getEvents() {
        return events;
    }

    public double getMaxDist() {
        return maxDist;
    }

    public int getPoints() {
        return points;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getDepthIterations() {
        return depthIterations;
    }

    public double getMaxDepth() {
        return maxDepth;
    }

    public HypocenterFinderSettings getFinderSettings() {
        return finderSettings;
    }
}

