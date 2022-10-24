package com.ead.notification.adapter.inbound.consumer;

import com.ead.notification.adapter.mapper.NotificationMapper;
import com.ead.notification.adapter.dto.NotificationCommandDTO;
import com.ead.notification.core.port.NotificationServicePort;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

@Component
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationServicePort notificationServicePort;
    private final NotificationMapper notificationMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ead.broker.queue.notification-command-queue.name}", durable = "true"),
            exchange = @Exchange(value = "${ead.broker.exchange.notification-command-exchange}", type = TOPIC, ignoreDeclarationExceptions = "true")
            ,key = "${ead.broker.key.notification-command-key}"
    ))
    public void listen(@Payload final NotificationCommandDTO dto){
        var domain = notificationMapper.toDomain(dto);
        notificationServicePort.save(domain);
    }

}
