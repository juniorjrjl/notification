package com.ead.notification.adapter.dto;

import com.ead.notification.core.domain.enumeration.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public record NotificationDTO(@JsonProperty("notificationStatus")
                              @NotNull
                              NotificationStatus notificationStatus) {
}
