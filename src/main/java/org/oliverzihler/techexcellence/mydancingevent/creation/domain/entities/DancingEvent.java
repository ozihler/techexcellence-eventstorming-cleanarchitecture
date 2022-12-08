package org.oliverzihler.techexcellence.mydancingevent.creation.domain.entities;

import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.DancingEventId;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.DateOfEvent;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.Description;
import org.oliverzihler.techexcellence.mydancingevent.creation.domain.values.Title;

import java.util.Objects;

public class DancingEvent {
    private final DancingEventId id;
    private final Title title;
    private final Description description;
    private final DateOfEvent dateOfEvent;

    private DancingEvent(DancingEventId id, Title title, Description description, DateOfEvent dateOfEvent) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateOfEvent = dateOfEvent;
    }

    public static DancingEvent createWith(String title, String description, long dateOfEvent) {
        Title validTitle = new Title(title);
        Description validDescription = new Description(description);
        DateOfEvent validDateOfEvent = new DateOfEvent(dateOfEvent);

        return new DancingEvent(DancingEventId.create(), validTitle, validDescription, validDateOfEvent);
    }


    public DancingEventId getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public DateOfEvent getDateOfEvent() {
        return dateOfEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DancingEvent that = (DancingEvent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DancingEvent{" +
                "title=" + title +
                ", id=" + id +
                '}';
    }
}
