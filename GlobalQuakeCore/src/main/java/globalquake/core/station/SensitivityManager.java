package globalquake.core.station;

import gqserver.api.packets.station.InputType;

public class SensitivityManager {
    private final double sensitivity;

    public SensitivityManager(double sensitivity) {
        this.sensitivity = sensitivity;
    }

    public double getSensitivity() {
        return sensitivity;
    }

    public boolean isSensitivityValid(InputType inputType) {
        return inputType != InputType.UNKNOWN && sensitivity > 0;
    }
}

