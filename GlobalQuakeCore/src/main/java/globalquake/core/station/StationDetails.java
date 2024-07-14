package globalquake.core.station;

public class StationDetails {
    private final String networkCode;
    private final String stationCode;
    private final String channelName;
    private final String locationCode;
    private final double lat;
    private final double lon;
    private final double alt;
    private final int id;

    public StationDetails(String networkCode, String stationCode, String channelName,
                          String locationCode, double lat, double lon, double alt, int id) {
        this.networkCode = networkCode;
        this.stationCode = stationCode;
        this.channelName = channelName;
        this.locationCode = locationCode;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.id = id;
    }

    public String getNetworkCode() {
        return networkCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }

    public double getAlt() {
        return alt;
    }

    public int getId() {
        return id;
    }

    public String getIdentifier(){
        return "%s %s %s %s".formatted(getNetworkCode(), getStationCode(), getChannelName(), getLocationCode());
    }
}

