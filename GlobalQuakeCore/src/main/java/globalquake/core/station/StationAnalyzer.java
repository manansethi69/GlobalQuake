package globalquake.core.station;

import globalquake.core.analysis.Analysis;
import globalquake.core.analysis.BetterAnalysis;
import globalquake.core.analysis.Event;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class StationAnalyzer {
    private static final int RATIO_HISTORY_SECONDS = 60;
    private final BetterAnalysis analysis;
    private final Deque<Double> ratioHistory = new LinkedBlockingDeque<>();

    public StationAnalyzer(AbstractStation station) {
        this.analysis = new BetterAnalysis(station);
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public Event getEventAt(long time, long tolerance) {
        if (analysis == null) {
            return null;
        }

        for (Event event : analysis.getDetectedEvents()) {
            if (!event.isValid()) {
                continue;
            }
            if (time >= event.getpWave() - tolerance && (!event.hasEnded() || time < event.getEnd() - tolerance)) {
                return event;
            }
        }

        return null;
    }

    public void second(long time) {
        if (analysis._maxRatio > 0) {
            ratioHistory.add(analysis._maxVelocity);
            analysis._maxRatioReset = true;

            if (ratioHistory.size() >= RATIO_HISTORY_SECONDS) {
                ratioHistory.remove();
            }
        }

        analysis.second(time);
    }

    public double getMaxRatio60S() {
        var opt = ratioHistory.stream().max(Double::compareTo);
        return opt.orElse(0.0);
    }

    public void reset() {
        ratioHistory.clear();
    }
}

