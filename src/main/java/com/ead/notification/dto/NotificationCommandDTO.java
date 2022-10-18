package com.ead.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

public record NotificationCommandDTO(@JsonProperty("title")String title,
                                     @JsonProperty("message")String message,
                                     @JsonProperty("userId")UUID userId){

    @Builder
    public NotificationCommandDTO {
    }
}
