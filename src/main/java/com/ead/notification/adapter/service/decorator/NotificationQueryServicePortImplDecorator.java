package com.ead.notification.adapter.service.decorator;

import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.PageInfo;
import com.ead.notification.core.port.NotificationQueryServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class NotificationQueryServicePortImplDecorator implements NotificationQueryServicePort {

    private NotificationQueryServicePort notificationQueryServicePort;

    @Override
    public List<NotificationDomain> findAllByUserId(final UUID userId, final PageInfo pageInfo) {
        return notificationQueryServicePort.findAllByUserId(userId, pageInfo);
    }

    @Transactional
    @Override
    public NotificationDomain findByIdAndUserId(final UUID id, final UUID userId) {
        return notificationQueryServicePort.findByIdAndUserId(id, userId);
    }
}
