package globalquake.core.station;

import globalquake.core.analysis.Analysis;
import globalquake.core.analysis.Event;
import globalquake.core.database.SeedlinkNetwork;
import gqserver.api.packets.station.InputType;

import java.util.Collection;

public abstract class AbstractStation {
	private final StationDetails details;
	private final StationStateManager stateManager;
	private final StationAnalyzer analyzer;
	private final NearbyStationManager nearbyStationManager;
	private final SensitivityManager sensitivityManager;
	private final SeedlinkNetwork seedlinkNetwork;

	public boolean disabled = false;
	public double _lastRenderSize;

	public AbstractStation(String networkCode, String stationCode, String channelName,
						   String locationCode, double lat, double lon, double alt,
						   int id, SeedlinkNetwork seedlinkNetwork, double sensitivity) {
		this.details = new StationDetails(networkCode, stationCode, channelName, locationCode, lat, lon, alt, id);
		this.stateManager = new StationStateManager();
		this.analyzer = new StationAnalyzer(this);
		this.nearbyStationManager = new NearbyStationManager();
		this.sensitivityManager = new SensitivityManager(sensitivity);
		this.seedlinkNetwork = seedlinkNetwork;
	}

	public StationDetails getDetails() {
		return details;
	}

	public StationStateManager getStateManager() {
		return stateManager;
	}

	public StationAnalyzer getAnalyzer() {
		return analyzer;
	}

	public NearbyStationManager getNearbyStationManager() {
		return nearbyStationManager;
	}

	public SensitivityManager getSensitivityManager() {
		return sensitivityManager;
	}

	public StationState getStateAt(long time) {
		return stateManager.getStateAt(time);
	}

	public Event getEventAt(long time, long tolerance) {
		return analyzer.getEventAt(time, tolerance);
	}

	public void reportState(StationState state, long time) {
		stateManager.reportState(state, time);
	}

	public double getAlt() {
		return details.getAlt();
	}

	public String getChannelName() {
		return details.getChannelName();
	}

	public double getLatitude() {
		return details.getLatitude();
	}

	public String getLocationCode() {
		return details.getLocationCode();
	}

	public double getLongitude() {
		return details.getLongitude();
	}

	public String getNetworkCode() {
		return details.getNetworkCode();
	}

	public String getStationCode() {
		return details.getStationCode();
	}

	public Analysis getAnalysis() {
		return analyzer.getAnalysis();
	}

	public boolean hasData() {
		return getDelayMS() != -1 && getDelayMS() < 5 * 60 * 1000;
	}

	public boolean hasDisplayableData() {
		return false;
	}

	public boolean isInEventMode() {
		return false;
	}

	public long getDelayMS() {
		return 0;
	}

	public void second(long time) {
		analyzer.second(time);
	}

	public double getMaxRatio60S() {
		return analyzer.getMaxRatio60S();
	}

	public void reset() {
		analyzer.reset();
	}

	public int getId() {
		return details.getId();
	}

	public void setNearbyStations(Collection<NearbyStationDistanceInfo> nearbyStations) {
		nearbyStationManager.setNearbyStations(nearbyStations);
	}

	public Collection<NearbyStationDistanceInfo> getNearbyStations() {
		return nearbyStationManager.getNearbyStations();
	}

	public void analyse() {
	}

	public SeedlinkNetwork getSeedlinkNetwork() {
		return seedlinkNetwork;
	}

	@Override
	public String toString() {
		return details.getIdentifier();
	}

	public String getIdentifier() {
		return details.getIdentifier();
	}

	public double getSensitivity() {
		return sensitivityManager.getSensitivity();
	}

	public abstract InputType getInputType();

	public boolean isSensitivityValid() {
		return sensitivityManager.isSensitivityValid(getInputType());
	}

	public void clear() {
		nearbyStationManager.clear();
	}
}
