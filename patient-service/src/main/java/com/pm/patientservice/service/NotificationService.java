package com.pm.patientservice.service;

import com.pm.patientservice.dto.NotificationMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${notification.queue.name}")
    private String notificationQueueName;

    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPatientCreatedNotification(NotificationMessageDTO message) {
        try {
            logger.info("Sending notification for patient: {}", message.getPatientId());
            rabbitTemplate.convertAndSend(notificationQueueName, message);
            logger.info("Successfully sent notification for patient: {}", message.getPatientId());
        } catch (Exception e) {
            logger.error("Failed to send notification for patient: {}", message.getPatientId(), e);
            // In production, you might want to store failed notifications for retry
        }
    }
}
