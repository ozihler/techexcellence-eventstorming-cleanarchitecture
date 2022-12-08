package org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter;

@FunctionalInterface
public interface PresentCreateDancingEventSuccess {
    void ofDancingEventCreation(CreateDancingEventOutput createDancingEventOutput);
}
