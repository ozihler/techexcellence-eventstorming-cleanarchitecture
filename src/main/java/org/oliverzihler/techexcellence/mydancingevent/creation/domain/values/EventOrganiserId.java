package org.oliverzihler.techexcellence.mydancingevent.creation.domain.values;

import org.oliverzihler.techexcellence.mydancingevent.creation.domain.exceptions.InvalidEventOrganiserIdException;
import org.springframework.util.StringUtils;

public record EventOrganiserId(String id) {
    public EventOrganiserId {
        if (!StringUtils.hasLength(id)) {
            throw new InvalidEventOrganiserIdException();
        }
    }
}
