package com.ead.notification.core.port;

import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationServicePort {

    NotificationDomain save(final NotificationDomain domain);

    List<NotificationDomain> findAllByUserId(final UUID userId, final PageInfo pageInfo);

    Optional<NotificationDomain> findByIdAndUserId(final UUID id, final UUID userId);

}
