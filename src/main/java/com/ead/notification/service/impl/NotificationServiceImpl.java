package com.ead.notification.service.impl;

import com.ead.notification.repository.NotificationRepository;
import com.ead.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

}
