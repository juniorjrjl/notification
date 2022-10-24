package com.ead.notification.core.service;

import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.PageInfo;
import com.ead.notification.core.port.NotificationPersistencePort;
import com.ead.notification.core.port.NotificationServicePort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ead.notification.core.domain.enumeration.NotificationStatus.CREATED;


@AllArgsConstructor
public class NotificationServicePortImpl implements NotificationServicePort {

    private final NotificationPersistencePort notificationPersistencePort;

    @Override
    public NotificationDomain save(final NotificationDomain domain) {
        return notificationPersistencePort.save(domain);
    }

    @Override
    public List<NotificationDomain> findAllByUserId(final UUID userId, final PageInfo pageInfo) {
        return notificationPersistencePort.findAllByUserIdAndNotificationStatus(userId, CREATED, pageInfo);
    }

    @Override
    public Optional<NotificationDomain> findByIdAndUserId(final UUID id, final UUID userId) {
        return notificationPersistencePort.findByIdAndUserId(id, userId);
    }
}
