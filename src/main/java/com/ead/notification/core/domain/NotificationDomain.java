package com.ead.notification.core.domain;

import com.ead.notification.core.domain.enumeration.NotificationStatus;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

public record NotificationDomain(

    UUID id,

    UUID userId,

    String title,

    String message,

    OffsetDateTime creationDate,

    NotificationStatus notificationStatus

){

    @Builder(toBuilder = true)
    public NotificationDomain{

    }

}
