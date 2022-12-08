package org.oliverzihler.techexcellence.mydancingevent.creation.adapter.out.dataaccess;

import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.gateway.FetchUnpublishedDancingEvents;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.gateway.UpdateUnpublishedDancingEvents;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities.UnpublishedDancingEvents;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.EventOrganiserId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUnpublishedDancingEventsRepository implements FetchUnpublishedDancingEvents, UpdateUnpublishedDancingEvents {
    Map<EventOrganiserId, UnpublishedDancingEvents> repository = new HashMap<>();

    @Override

    public void withDancingEvents(UnpublishedDancingEvents unpublishedDancingEvents) {
        repository.put(unpublishedDancingEvents.getEventOrganiserId(), unpublishedDancingEvents);

    }

    @Override
    public Optional<UnpublishedDancingEvents> tryByEventOrganiserId(EventOrganiserId eventOrganiserId) {
        return Optional.ofNullable(repository.get(eventOrganiserId));
    }
}
