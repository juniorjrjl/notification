package com.ead.notification.repository;

import com.ead.notification.enumeration.NotificationStatus;
import com.ead.notification.model.NotificationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationModel, UUID> {

    Page<NotificationModel> findAllByUserIdAndNotificationStatus(final UUID userId, final NotificationStatus notificationStatus, final Pageable pageable);

    Optional<NotificationModel> findByIdAndUserId(final UUID id, final UUID userId);

}
