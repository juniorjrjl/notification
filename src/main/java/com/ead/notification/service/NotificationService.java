package com.ead.notification.service;

import com.ead.notification.model.NotificationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface NotificationService {
    NotificationModel save(final NotificationModel model);

    Page<NotificationModel> findAllByUserId(final UUID userId, final Pageable pageable);

    Optional<NotificationModel> findByIdAndUserId(final UUID id, final UUID userId);
}
