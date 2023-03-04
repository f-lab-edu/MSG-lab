package com.example.common.domain;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Notification {
    @NotEmpty
    private String title;
    private String body;

    public void putPrefixIfAbsent() {
        if (hasCommercialPrefix()) {
            return;
        }
        addPrefix();
    }

    boolean hasCommercialPrefix() {
        return title.startsWith("(광고)");
    }

    void addPrefix() {
        title = "(광고) " + title;
    }
}
