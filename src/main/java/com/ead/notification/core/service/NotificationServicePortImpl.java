package com.ead.notification.core.service;

import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.PageInfo;
import com.ead.notification.core.domain.enumeration.NotificationStatus;
import com.ead.notification.core.port.NotificationPersistencePort;
import com.ead.notification.core.port.NotificationQueryServicePort;
import com.ead.notification.core.port.NotificationServicePort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ead.notification.core.domain.enumeration.NotificationStatus.CREATED;


@AllArgsConstructor
public class NotificationServicePortImpl implements NotificationServicePort {

    private final NotificationPersistencePort notificationPersistencePort;
    private final NotificationQueryServicePort notificationQueryServicePort;

    @Override
    public NotificationDomain save(final NotificationDomain domain) {
        return notificationPersistencePort.save(domain);
    }

    @Override
    public NotificationDomain update(final UUID id, final UUID userId, final NotificationStatus status) {
        var domain = notificationQueryServicePort.findByIdAndUserId(id, userId);
        return save(domain.toBuilder().notificationStatus(status).build());
    }

}
