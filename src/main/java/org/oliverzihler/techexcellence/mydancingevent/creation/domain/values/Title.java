package org.oliverzihler.techexcellence.mydancingevent.creation.domain.values;

import static org.springframework.util.StringUtils.hasLength;

public record Title(String title) {
    public Title(String title) {
        this.title = hasLength(title) ? title : "";
    }
}
