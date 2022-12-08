package org.oliverzihler.techexcellence.mydancingevent.creation.domain.values;

import static org.springframework.util.StringUtils.hasLength;

public record Description(String description) {
    public Description(String description) {
        this.description = hasLength(description) ? description : "";
    }
}
