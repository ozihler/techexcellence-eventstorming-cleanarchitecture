package org.oliverzihler.techexcellence.mydancingevent.creation.adapter.in.web;

import org.oliverzihler.techexcellence.mydancingevent.creation.adapter.out.web.CreateDancingEventViewModel;
import org.oliverzihler.techexcellence.mydancingevent.creation.adapter.out.web.JsonDancingEventCreatedView;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.in.CreateDancingEvent;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.in.CreateDancingEventInput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateDancingEventResource {
    private final CreateDancingEvent createDancingEvent;

    public CreateDancingEventResource(CreateDancingEvent createDancingEvent) {
        this.createDancingEvent = createDancingEvent;
    }


    @PostMapping(path = "/dancing-events", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateDancingEventViewModel> createDancingEvent(@RequestBody CreateDancingEventInput inputData) {

        JsonDancingEventCreatedView view = new JsonDancingEventCreatedView();

        createDancingEvent.executeWith(inputData, view, view);

        return view.model();
    }


}
