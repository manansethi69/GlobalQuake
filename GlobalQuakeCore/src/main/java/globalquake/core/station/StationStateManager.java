package globalquake.core.station;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class StationStateManager {
    public static final long INTERVAL_STORAGE_TIME = 30 * 60 * 1000;
    public static final long INTERVAL_MAX_GAP = 5 * 1000;

    private final Deque<StationInterval> intervals = new ConcurrentLinkedDeque<>();

    public StationState getStateAt(long time) {
        for (StationInterval interval : intervals) {
            if (time >= interval.getStart() && time < interval.getEnd()) {
                return interval.getState();
            }
        }
        return StationState.UNKNOWN;
    }

    public void reportState(StationState state, long time) {
        while (intervals.peekFirst() != null && time - intervals.peekFirst().getEnd() > INTERVAL_STORAGE_TIME) {
            intervals.removeFirst();
        }
        StationInterval lastInterval = intervals.peekLast();
        if (lastInterval == null) {
            intervals.add(new StationInterval(time, time, state));
            return;
        }

        if (time - lastInterval.getEnd() > INTERVAL_MAX_GAP) {
            intervals.add(new StationInterval(time, time, state));
            return;
        }

        lastInterval.setEnd(time);

        if (lastInterval.getState() != state) {
            intervals.add(new StationInterval(time, time, state));
        }
    }

    public Deque<StationInterval> getIntervals() {
        return intervals;
    }
}

