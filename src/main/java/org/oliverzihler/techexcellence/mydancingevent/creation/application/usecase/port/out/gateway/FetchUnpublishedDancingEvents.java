package org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.gateway;

import org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities.UnpublishedDancingEvents;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.EventOrganiserId;

import java.util.Optional;

@FunctionalInterface
public interface FetchUnpublishedDancingEvents {
    Optional<UnpublishedDancingEvents> tryByEventOrganiserId(EventOrganiserId eventOrganiserId);

    default UnpublishedDancingEvents ofEventOrganiser(EventOrganiserId eventOrganiserId) throws EventOrganiserNotFoundException {
        return tryByEventOrganiserId(eventOrganiserId).orElseThrow(EventOrganiserNotFoundException::new);
    }
}
