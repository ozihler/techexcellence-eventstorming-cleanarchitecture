package org.oliverzihler.techexcellence.mydancingevent.creation.adapter.out.web;

import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter.CreateDancingEventOutput;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter.PresentCreateDancingEventFailure;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter.PresentCreateDancingEventSuccess;
import org.springframework.http.ResponseEntity;

public class JsonDancingEventCreatedView implements PresentCreateDancingEventSuccess, PresentCreateDancingEventFailure {

    private ResponseEntity<CreateDancingEventViewModel> viewModel;

    @Override
    public void ofDancingEventCreation(Exception exception) {
        viewModel = ResponseEntity.internalServerError().body(new CreateDancingEventViewModel());
    }

    @Override
    public void ofDancingEventCreation(CreateDancingEventOutput createDancingEventOutput) {
        viewModel = ResponseEntity.ok(new CreateDancingEventViewModel());
    }

    public ResponseEntity<CreateDancingEventViewModel> model() {
        return viewModel;
    }
}
