package com.ead.notification.core.port;

import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.PageInfo;
import com.ead.notification.core.domain.enumeration.NotificationStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationPersistencePort {

    NotificationDomain save(final NotificationDomain domain);

    List<NotificationDomain> findAllByUserIdAndNotificationStatus(final UUID userId, final NotificationStatus notificationStatus, final PageInfo pageInfo);

    Optional<NotificationDomain> findByIdAndUserId(final UUID id, final UUID userId);

}
