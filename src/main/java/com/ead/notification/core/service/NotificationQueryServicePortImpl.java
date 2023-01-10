package com.ead.notification.core.service;

import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.PageInfo;
import com.ead.notification.core.exception.DomainNotFoundException;
import com.ead.notification.core.port.NotificationPersistencePort;
import com.ead.notification.core.port.NotificationQueryServicePort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import static com.ead.notification.core.domain.enumeration.NotificationStatus.CREATED;

@AllArgsConstructor
public class NotificationQueryServicePortImpl implements NotificationQueryServicePort {

    private final NotificationPersistencePort notificationPersistencePort;

    @Override
    public List<NotificationDomain> findAllByUserId(final UUID userId, final PageInfo pageInfo) {
        return notificationPersistencePort.findAllByUserIdAndNotificationStatus(userId, CREATED, pageInfo);
    }

    @Override
    public NotificationDomain findByIdAndUserId(final UUID id, final UUID userId) {
        return notificationPersistencePort.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new DomainNotFoundException(String.format("A Notificação do usuário %s com id %s não foi encontrada", userId, id)));
    }

}
