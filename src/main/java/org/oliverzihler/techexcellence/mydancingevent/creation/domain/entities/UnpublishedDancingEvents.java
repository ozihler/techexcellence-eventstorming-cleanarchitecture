package org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities;

import org.oliverzihler.techexcellence.mydancingevent.creation.domain.exceptions.NumberOfUnpublishedDancingEventsExceededException;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.EventOrganiserId;

import java.util.List;

public class UnpublishedDancingEvents {
    private static final int MAX_NUMBER_OF_UNPUBLISHED_DANCING_EVENTS = 5;
    private final EventOrganiserId eventOrganiserId;

    private final List<DancingEvent> unpublishedDancingEvents;

    public UnpublishedDancingEvents(EventOrganiserId eventOrganiserId, List<DancingEvent> unpublishedDancingEvents) {
        this.eventOrganiserId = eventOrganiserId;
        this.unpublishedDancingEvents = unpublishedDancingEvents;
    }

    public int count() {
        return unpublishedDancingEvents.size();
    }


    public void add(DancingEvent dancingEvent) throws NumberOfUnpublishedDancingEventsExceededException {
        if (numberOfUnpublishedDancingEventsExceeded()) {
            throw new NumberOfUnpublishedDancingEventsExceededException();
        }
        this.unpublishedDancingEvents.add(dancingEvent);
    }

    private boolean numberOfUnpublishedDancingEventsExceeded() {
        return count() >= MAX_NUMBER_OF_UNPUBLISHED_DANCING_EVENTS;
    }

    public EventOrganiserId getEventOrganiserId() {
        return eventOrganiserId;
    }
}
