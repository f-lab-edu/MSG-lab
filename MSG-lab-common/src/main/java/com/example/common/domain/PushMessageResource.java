package com.example.common.domain;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.Getter;

@Getter
public class PushMessageResource {
    private final ResourceBundle bundle = ResourceBundle.getBundle("PushMessageResource", Locale.KOREA);

    public String getCommercialPrefix() {
        return bundle.getString("commercial.prefix");
    }

    public String getUnsubscribeMethod() {
        return bundle.getString("unsubscribe.method");
    }
}
