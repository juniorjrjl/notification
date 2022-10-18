package com.ead.notification.dto;

import com.ead.notification.enumeration.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public record NotificationDTO(@JsonProperty("notificationStatus")
                              @NotNull
                              NotificationStatus notificationStatus) {
}
