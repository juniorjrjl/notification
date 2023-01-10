package com.ead.notification.adapter.outbound.persistence.entity;

import com.ead.notification.core.domain.enumeration.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.fasterxml.jackson.databind.util.StdDateFormat.DATE_FORMAT_STR_ISO8601;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_NOTIFICATIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false)
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_STR_ISO8601)
    private OffsetDateTime creationDate;

    @Column(nullable = false)
    @Enumerated(STRING)
    private NotificationStatus notificationStatus;

    @PrePersist
    public void beforeSave(){
        this.creationDate = OffsetDateTime.now();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NotificationEntity that = (NotificationEntity) o;
        return Objects.equals(id, that.id) && userId.equals(that.userId) && title.equals(that.title) &&
                message.equals(that.message) && Objects.equals(creationDate, that.creationDate) &&
                notificationStatus == that.notificationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, message, creationDate, notificationStatus);
    }
}
