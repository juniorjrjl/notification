package com.ead.notification.adapter.inbound.controller;


import com.ead.notification.adapter.config.security.UserDetailsImpl;
import com.ead.notification.adapter.dto.NotificationDTO;
import com.ead.notification.core.domain.NotificationDomain;
import com.ead.notification.core.domain.PageInfo;
import com.ead.notification.core.exception.DomainNotFoundException;
import com.ead.notification.core.port.NotificationQueryServicePort;
import com.ead.notification.core.port.NotificationServicePort;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("users/{userId}/notifications")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@Log4j2
public class UserNotificationController {

    private final NotificationServicePort notificationServicePort;
    private final NotificationQueryServicePort notificationQueryServicePort;

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping
    public Page<NotificationDomain> getAllByUserId(@PathVariable final UUID userId,
                                                                   @PageableDefault(sort = "id", direction = ASC)final Pageable pageable,
                                                                   final Authentication authentication){
        log.info("[getAllByUserID] notifications requested by {}", ((UserDetailsImpl)authentication.getPrincipal()).id());
        var pageInfo = new PageInfo(pageable.getPageNumber(), pageable.getPageSize());
        var response = notificationQueryServicePort.findAllByUserId(userId, pageInfo);
        return new PageImpl<>(response, pageable, response.size());
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @PutMapping("{notificationId}")
    public NotificationDomain update(@PathVariable final UUID userId,
                                         @PathVariable final UUID notificationId,
                                         @RequestBody @Valid final NotificationDTO request){
        return notificationServicePort.update(notificationId, userId, request.notificationStatus());
    }

}
