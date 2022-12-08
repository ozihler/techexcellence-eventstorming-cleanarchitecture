package org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.out.presenter;

@FunctionalInterface
public interface PresentCreateDancingEventFailure {
    void ofDancingEventCreation(Exception exception);
}
