
package com.tsfn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tsfn.models.NotificatioTwilio;
import com.tsfn.service.SmsService;
import com.tsfn.service.EmailNotificationService;
import com.tsfn.service.WhatsappService;

@Service
public class KafkaConsumerImpl {

    private final SmsService smsService;
    private final EmailNotificationService emailNotificationService;
    private final WhatsappService whatsappService;

    public KafkaConsumerImpl(SmsService smsService, EmailNotificationService emailNotificationService, WhatsappService whatsappService) {
        this.smsService = smsService;
        this.emailNotificationService = emailNotificationService;
        this.whatsappService = whatsappService;
    }

    @KafkaListener(topics = "NotificationTopic", groupId = "groupId")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        // Parse the message and call the appropriate function based on the type of "via"
        if (message == null) {
            System.out.println("Received message is null.");
            return;
        }
        
        if (message == "") {
            System.out.println("the condition is not true !");
            return;
        }
        
        String[] parts = message.split("SPLIT");
        if (parts.length != 3) {
            System.out.println("Invalid message format. Expected format: 'viaSPLITtoSPLITnotification'. Received message: " + message);
            return;
        }

        for(String p : parts) {
            System.out.println("Part message format: " + p.trim());
        }

        String via = parts[0].trim();
        String to = parts[1].trim();
        String notification = parts[2].trim();
     
        switch (via) {
            case "SMS"://i need to do set here 
                smsService.sendSms(to, notification);
                break;
            case "Email":
                emailNotificationService.sendNotification(to, notification);
                break;
            case "WhatsApp":
                whatsappService.sendWhatsAppMessage(to,notification);
                break;
            default:
                System.out.println("Unsupported 'via' type: " + via);
        }
    }
}
