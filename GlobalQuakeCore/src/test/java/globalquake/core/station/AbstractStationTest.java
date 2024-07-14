package globalquake.core.station;

import gqserver.api.packets.station.InputType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractStationTest {

    @Test
    public void testIntervals(){
        AbstractStation abstractStation = new GlobalStation("", "", "", "",5,5,5,5,null, -1, InputType.UNKNOWN);
        StationInterval int1 = new StationInterval(10, 50, StationState.INACTIVE);
        StationInterval int2 = new StationInterval(50, 70, StationState.ACTIVE);
        StationInterval int3 = new StationInterval(100, 150, StationState.ACTIVE);
        abstractStation.getStateManager().getIntervals().add(int1);
        abstractStation.getStateManager().getIntervals().add(int2);
        abstractStation.getStateManager().getIntervals().add(int3);
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(0));
        assertEquals(StationState.INACTIVE, abstractStation.getStateManager().getStateAt(10));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(50));
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(80));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(120));
    }

    @Test
    public void testReport(){
        AbstractStation abstractStation = new GlobalStation("", "", "", "",5,5,5,5,null, -1, InputType.UNKNOWN);
        abstractStation.getStateManager().reportState(StationState.ACTIVE, 0);
        abstractStation.getStateManager().reportState(StationState.ACTIVE, 10);

        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(-10));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(0));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(5));
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(10));
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(20));

        abstractStation.getStateManager().reportState(StationState.ACTIVE, 50);
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(5));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(10));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(20));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(49));


        abstractStation.getStateManager().reportState(StationState.ACTIVE, 60 + StationStateManager.INTERVAL_MAX_GAP);
        abstractStation.getStateManager().reportState(StationState.ACTIVE, 70 + StationStateManager.INTERVAL_MAX_GAP);
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(50));
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(60));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(60 + StationStateManager.INTERVAL_MAX_GAP));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(65 + StationStateManager.INTERVAL_MAX_GAP));

        abstractStation.getStateManager().reportState(StationState.ACTIVE, 51 + StationStateManager.INTERVAL_STORAGE_TIME);
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(-10));
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(0));
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(5));
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(10));
        assertEquals(StationState.UNKNOWN, abstractStation.getStateManager().getStateAt(20));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(60 + StationStateManager.INTERVAL_MAX_GAP));
        assertEquals(StationState.ACTIVE, abstractStation.getStateManager().getStateAt(65 + StationStateManager.INTERVAL_MAX_GAP));
    }
}
