package usecase;

import org.junit.jupiter.api.Test;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.CreateDancingEventUseCase;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.in.CreateDancingEventInput;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.gateway.AddDancingEventFailedException;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.gateway.EventOrganiserNotFoundException;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter.CreateDancingEventOutput;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities.DancingEvent;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities.UnpublishedDancingEvents;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.exceptions.NumberOfUnpublishedDancingEventsExceededException;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.DateOfEvent;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.Description;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.Title;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateDancingEventUseCaseShould {
    private static long validDancingEventInTheFuture() {
        return Instant.now().toEpochMilli()+10;
    }

    @Test
    void createADancingEvent() {
        CreateDancingEventInput input = new CreateDancingEventInput(
                "inexistent",
                "Title of Dancing Event",
                "This is what the event is all about",
                validDancingEventInTheFuture());

        List<UnpublishedDancingEvents> updatedUnpublishedDancingEvents = new ArrayList<>();

        useCase = new CreateDancingEventUseCase(
                (id) -> Optional.of(new UnpublishedDancingEvents(id, new ArrayList<>())),
                (updatedUnpublishedDancingEvents::add)
        );

        List<CreateDancingEventOutput> outputs = new ArrayList<>();

        useCase.executeWith(input, outputs::add, null);

        assertEquals(1, outputs.size());
        assertEquals(1, updatedUnpublishedDancingEvents.get(0).count());
    }

    @Test
    void limitTheNumberOfCreatedDancingEventsTo5AtATime() {
        CreateDancingEventInput input = new CreateDancingEventInput(
                "inexistent",
                "Title of Dancing Event",
                "This is what the event is all about",
                validDancingEventInTheFuture());


        useCase = new CreateDancingEventUseCase(
                (id) -> Optional.of(new UnpublishedDancingEvents(id, List.of(testDancingEvent(), testDancingEvent(), testDancingEvent(), testDancingEvent(), testDancingEvent()))),
                (dancingEvents -> {
                })
        );

        List<Exception> exceptions = new ArrayList<>();

        useCase.executeWith(input, null, exceptions::add);

        assertEquals(NumberOfUnpublishedDancingEventsExceededException.class, exceptions.get(0).getClass());
    }

    @Test
    void requireAnEventOrganiserToExistInOrderToAddANewDancingEvent() {
        CreateDancingEventInput input = new CreateDancingEventInput(
                "inexistent",
                "Title of Dancing Event",
                "This is what the event is all about",
                validDancingEventInTheFuture());

        useCase = new CreateDancingEventUseCase(
                (id) -> Optional.empty(),
                null
        );

        List<Exception> exceptions = new ArrayList<>();

        useCase.executeWith(input, null, exceptions::add);

        assertEquals(EventOrganiserNotFoundException.class, exceptions.get(0).getClass());
    }

    @Test
    void informEventOrganisersWhenAddingADancingEventFailed() {
        CreateDancingEventInput input = new CreateDancingEventInput(
                "inexistent",
                "Title of Dancing Event",
                "This is what the event is all about",
                validDancingEventInTheFuture());


        useCase = new CreateDancingEventUseCase(
                (id) -> Optional.of(new UnpublishedDancingEvents(id, new ArrayList<>())),
                dancingEvents -> {
                    throw new AddDancingEventFailedException();
                }
        );

        List<Exception> exceptions = new ArrayList<>();

        useCase.executeWith(input, null, exceptions::add);

        assertEquals(AddDancingEventFailedException.class, exceptions.get(0).getClass());
    }

    private CreateDancingEventUseCase useCase;


    private DancingEvent testDancingEvent() {
        return DancingEvent.createWith("Title", "Description", validDancingEventInTheFuture());
    }
}