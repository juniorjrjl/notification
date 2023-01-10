package com.ead.notification.adapter.service.decorator;

import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.enumeration.NotificationStatus;
import com.ead.notification.core.port.NotificationServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Component
public class NotificationServicePortImplDecorator implements NotificationServicePort {

    private final NotificationServicePort notificationServicePort;

    @Transactional
    @Override
    public NotificationDomain save(final NotificationDomain domain) {
        return notificationServicePort.save(domain);
    }

    @Transactional
    @Override
    public NotificationDomain update(final UUID id, final UUID userId, final NotificationStatus status) {
        return notificationServicePort.update(id, userId, status);
    }
}
