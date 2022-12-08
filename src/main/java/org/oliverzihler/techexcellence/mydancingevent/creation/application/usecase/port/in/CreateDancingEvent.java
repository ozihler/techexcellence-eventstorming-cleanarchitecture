package org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.in;

import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter.PresentCreateDancingEventFailure;
import org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter.PresentCreateDancingEventSuccess;

@FunctionalInterface
public interface CreateDancingEvent {
    void executeWith(CreateDancingEventInput input, PresentCreateDancingEventSuccess presentSuccess, PresentCreateDancingEventFailure presentFailure);
}
