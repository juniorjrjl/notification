package com.ead.notification.adapter.config;

import com.ead.notification.NotificationApplication;
import com.ead.notification.adapter.service.decorator.NotificationQueryServicePortImplDecorator;
import com.ead.notification.adapter.service.decorator.NotificationServicePortImplDecorator;
import com.ead.notification.core.port.NotificationPersistencePort;
import com.ead.notification.core.port.NotificationQueryServicePort;
import com.ead.notification.core.port.NotificationServicePort;
import com.ead.notification.core.service.NotificationQueryServicePortImpl;
import com.ead.notification.core.service.NotificationServicePortImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackageClasses = NotificationApplication.class)
public class BeanConfiguration {

    @Primary
    @Bean
    public NotificationServicePort notificationServicePortImpl(final NotificationPersistencePort persistence,
                                                               final NotificationQueryServicePort notificationQueryServicePort){
        var notificationServicePort = new NotificationServicePortImpl(persistence, notificationQueryServicePort);
        return new NotificationServicePortImplDecorator(notificationServicePort);
    }

    @Primary
    @Bean
    public NotificationQueryServicePort notificationQueryServicePort(final NotificationPersistencePort persistence){
        var notificationQueryServicePort = new NotificationQueryServicePortImpl(persistence);
        return new NotificationQueryServicePortImplDecorator(notificationQueryServicePort);
    }


}
