package com.example.msglab.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Notification {
    @NotEmpty
    private String title;
    private String body;
}
