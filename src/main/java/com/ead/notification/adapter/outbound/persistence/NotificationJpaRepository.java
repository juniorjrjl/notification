package com.ead.notification.adapter.outbound.persistence;

import com.ead.notification.adapter.outbound.persistence.entity.NotificationEntity;
import com.ead.notification.core.domain.enumeration.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, UUID> {

    Page<NotificationEntity> findAllByUserIdAndNotificationStatus(final UUID userId, final NotificationStatus notificationStatus, final Pageable pageable);

    Optional<NotificationEntity> findByIdAndUserId(final UUID id, final UUID userId);

}
