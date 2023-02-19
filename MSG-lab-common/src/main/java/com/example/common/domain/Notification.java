package com.example.common.domain;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @NotEmpty
    private String title;
    private String body;

    public void putPrefixIfAbsent() {
        if (hasPrefix()) {
            return;
        }
        addPrefix();
    }

    boolean hasPrefix() {
        return title.startsWith("(광고)");
    }

    void addPrefix() {
        title = "(광고) " + title;
    }
}
