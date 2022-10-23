package com.ead.notification.controller;

import com.ead.notification.config.security.UserDetailsImpl;
import com.ead.notification.dto.NotificationDTO;
import com.ead.notification.model.NotificationModel;
import com.ead.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
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

    private final NotificationService notificationService;

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping
    public ResponseEntity<Page<NotificationModel>> getAllByUserId(@PathVariable final UUID userId,
                                                                  @PageableDefault(sort = "id", direction = ASC)final Pageable pageable,
                                                                  final Authentication authentication){
        log.info("[getAllByUserID] notifications requested by {}", ((UserDetailsImpl)authentication.getPrincipal()).id());
        return ResponseEntity.status(OK).body(notificationService.findAllByUserId(userId, pageable));
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @PutMapping("{notificationId}")
    public ResponseEntity<Object> update(@PathVariable final UUID userId,
                                         @PathVariable final UUID notificationId,
                                         @RequestBody @Valid final NotificationDTO request){
        var modelOptional = notificationService.findByIdAndUserId(notificationId, userId);
        if (modelOptional.isEmpty()){
            return ResponseEntity.status(NOT_FOUND).body("Notification not found!");
        }
        var model = modelOptional.get();
        model.setNotificationStatus(request.notificationStatus());
        notificationService.save(model);
        return ResponseEntity.status(OK).body(model);
    }

}
