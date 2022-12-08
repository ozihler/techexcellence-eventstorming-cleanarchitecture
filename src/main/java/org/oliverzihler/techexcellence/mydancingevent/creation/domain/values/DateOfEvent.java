package org.oliverzihler.techexcellence.mydancingevent.creation.domain.values;

import org.oliverzihler.techexcellence.mydancingevent.creation.domain.exceptions.EventCannotBeScheduledInThePastException;

import java.time.Instant;

public record DateOfEvent(long dateOfEvent) {
    public DateOfEvent {
        if (dateOfEvent < Instant.now().toEpochMilli()) {
            throw new EventCannotBeScheduledInThePastException();
        }
    }
}
