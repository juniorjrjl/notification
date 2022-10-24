package com.ead.notification.adapter.outbound.persistence;

import com.ead.notification.adapter.mapper.NotificationMapper;
import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.PageInfo;
import com.ead.notification.core.domain.enumeration.NotificationStatus;
import com.ead.notification.core.port.NotificationPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class NotificationPersistencePortImpl implements NotificationPersistencePort {

    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public NotificationDomain save(final NotificationDomain domain) {
        var entity = notificationJpaRepository.save(notificationMapper.toEntity(domain));
        return notificationMapper.toDomain(entity);
    }

    @Override
    public List<NotificationDomain> findAllByUserIdAndNotificationStatus(final UUID userId, final NotificationStatus notificationStatus, final PageInfo pageInfo) {
        var pageable = PageRequest.of(pageInfo.pageNumber(), pageInfo.pageSize());
        return notificationJpaRepository.findAllByUserIdAndNotificationStatus(userId, notificationStatus, pageable).stream()
                .map(notificationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotificationDomain> findByIdAndUserId(final UUID id, final UUID userId) {
        var optionalEntity = notificationJpaRepository.findByIdAndUserId(id, userId);
        return optionalEntity.map(notificationMapper::toDomain);
    }
}
