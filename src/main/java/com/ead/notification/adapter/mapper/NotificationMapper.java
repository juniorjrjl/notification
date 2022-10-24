package com.ead.notification.adapter.mapper;

import com.ead.notification.adapter.dto.NotificationCommandDTO;
import com.ead.notification.adapter.outbound.persistence.entity.NotificationEntity;
import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.enumeration.NotificationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;

import static com.ead.notification.core.domain.enumeration.NotificationStatus.CREATED;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface NotificationMapper {

    NotificationEntity toEntity(final NotificationDomain domain);

    NotificationDomain toDomain(final NotificationEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "notificationStatus", expression = "java(createdStatus())")
    @Mapping(target = "creationDate", expression = "java(currentDateTime())")
    NotificationDomain toDomain(final NotificationCommandDTO dto);

    default NotificationStatus createdStatus(){
        return CREATED;
    }

    default OffsetDateTime currentDateTime(){
        return OffsetDateTime.now();
    }

}
