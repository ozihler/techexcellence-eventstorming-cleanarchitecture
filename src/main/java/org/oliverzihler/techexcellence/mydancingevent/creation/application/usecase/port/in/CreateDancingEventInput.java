package org.oliverzihler.techexcellence.mydancingevent.creation.application.usecase.port.in;

public record CreateDancingEventInput(String eventOrganiserId, String title, String description, long dateOfEvent) {
}
