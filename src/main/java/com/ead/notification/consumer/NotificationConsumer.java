package com.ead.notification.consumer;

import com.ead.notification.dto.NotificationCommandDTO;
import com.ead.notification.model.NotificationModel;
import com.ead.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

import static com.ead.notification.enumeration.NotificationStatus.CREATED;
import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

@Component
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ead.broker.queue.notification-command-queue.name}", durable = "true"),
            exchange = @Exchange(value = "${ead.broker.exchange.notification-command-exchange}", type = TOPIC, ignoreDeclarationExceptions = "true")
            ,key = "${ead.broker.key.notification-command-key}"
    ))
    public void listen(@Payload final NotificationCommandDTO dto){
        var model = new NotificationModel();
        BeanUtils.copyProperties(dto, model);
        model.setCreationDate(OffsetDateTime.now());
        model.setNotificationStatus(CREATED);
        notificationService.save(model);
    }

}
