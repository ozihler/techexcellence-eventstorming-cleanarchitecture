package org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter;

import org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities.DancingEvent;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.DateOfEvent;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.Description;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.Title;

public record CreateDancingEventOutput(
        Title title,
        Description description,
        DateOfEvent dateOfEvent) {
    public static CreateDancingEventOutput from(DancingEvent dancingEvent) {
        return new CreateDancingEventOutput(dancingEvent.getTitle(), dancingEvent.getDescription(), dancingEvent.getDateOfEvent());
    }
}
