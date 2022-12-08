package org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.gateway;

import org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities.UnpublishedDancingEvents;

@FunctionalInterface
public interface UpdateUnpublishedDancingEvents {
    void withDancingEvents(UnpublishedDancingEvents unpublishedDancingEvents) throws AddDancingEventFailedException;
}
