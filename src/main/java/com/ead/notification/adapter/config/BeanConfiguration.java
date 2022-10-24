package com.ead.notification.adapter.config;

import com.ead.notification.NotificationApplication;
import com.ead.notification.core.port.NotificationPersistencePort;
import com.ead.notification.core.service.NotificationServicePortImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = NotificationApplication.class)
public class BeanConfiguration {

    @Bean
    public NotificationServicePortImpl notificationServicePortImpl(final NotificationPersistencePort persistence){
        return new NotificationServicePortImpl(persistence);
    }


}
