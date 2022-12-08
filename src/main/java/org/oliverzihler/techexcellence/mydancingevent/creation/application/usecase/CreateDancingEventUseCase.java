package org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase;

import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.in.CreateDancingEvent;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.in.CreateDancingEventInput;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.gateway.FetchUnpublishedDancingEvents;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.gateway.UpdateUnpublishedDancingEvents;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter.CreateDancingEventOutput;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter.PresentCreateDancingEventFailure;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter.PresentCreateDancingEventSuccess;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities.DancingEvent;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities.UnpublishedDancingEvents;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.EventOrganiserId;
import org.springframework.stereotype.Service;

@Service
public class CreateDancingEventUseCase implements CreateDancingEvent {
    private final UpdateUnpublishedDancingEvents updateUnpublishedDancingEvents;
    private final FetchUnpublishedDancingEvents fetchDancingEvents;

    public CreateDancingEventUseCase(FetchUnpublishedDancingEvents fetchDancingEvents, UpdateUnpublishedDancingEvents updateUnpublishedDancingEvents) {
        this.fetchDancingEvents = fetchDancingEvents;
        this.updateUnpublishedDancingEvents = updateUnpublishedDancingEvents;
    }


    @Override
    public void executeWith(CreateDancingEventInput inputData, PresentCreateDancingEventSuccess presentSuccess, PresentCreateDancingEventFailure presentFailure) {
        try {
            EventOrganiserId eventOrganiserId = new EventOrganiserId(inputData.eventOrganiserId());

            UnpublishedDancingEvents unpublishedDancingEvents = fetchDancingEvents.ofEventOrganiser(eventOrganiserId);

            DancingEvent dancingEvent = DancingEvent.createWith(inputData.title(), inputData.description(), inputData.dateOfEvent());

            unpublishedDancingEvents.add(dancingEvent);

            updateUnpublishedDancingEvents.withDancingEvents(unpublishedDancingEvents);

            CreateDancingEventOutput output = CreateDancingEventOutput.from(dancingEvent);

            presentSuccess.ofDancingEventCreation(output);
        } catch (Exception e) {
            presentFailure.ofDancingEventCreation(e);
        }
    }

}
