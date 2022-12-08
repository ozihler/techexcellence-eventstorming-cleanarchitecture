package org.oliverzihler.techexcellence.mydancingevent.creation.domain.values;

public record DancingEventId(String id) {
    private static int idCounter = 0;

    public static DancingEventId create() {
        return new DancingEventId(String.valueOf(idCounter++));
    }
}
