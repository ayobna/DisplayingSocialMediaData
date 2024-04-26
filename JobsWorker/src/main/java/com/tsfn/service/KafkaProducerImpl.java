package com.tsfn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl {

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	public void sendMessage(String message)
	{
		kafkaTemplate.send("NotificationTopic", message);
	}
}