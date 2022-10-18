package com.ead.notification.service.impl;

import com.ead.notification.model.NotificationModel;
import com.ead.notification.repository.NotificationRepository;
import com.ead.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.ead.notification.enumeration.NotificationStatus.CREATED;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public NotificationModel save(final NotificationModel model) {
        return notificationRepository.save(model);
    }

    @Override
    public Page<NotificationModel> findAllByUserId(final UUID userId, final Pageable pageable) {
        return notificationRepository.findAllByUserIdAndNotificationStatus(userId, CREATED, pageable);
    }

    @Override
    public Optional<NotificationModel> findByIdAndUserId(final UUID id, final UUID userId) {
        return notificationRepository.findByIdAndUserId(id, userId);
    }
}
