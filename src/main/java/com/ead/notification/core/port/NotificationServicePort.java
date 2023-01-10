package com.ead.notification.core.port;

import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.PageInfo;
import com.ead.notification.core.domain.enumeration.NotificationStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationServicePort {

    NotificationDomain save(final NotificationDomain domain);

    NotificationDomain update(final UUID id, final UUID userId, final NotificationStatus status);

}
