package com.mbenz.storageservice.kafka;

import com.mbenz.storageservice.dto.UserRequest;
import com.mbenz.storageservice.dto.UserResponse;
import com.mbenz.storageservice.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class KafKaProducerService
{
    private static final Logger logger = LoggerFactory.getLogger(KafKaProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Retryable(maxAttempts = 3, include = Exception.class, backoff = @Backoff(delay = 1000))
    public void sendMessage(String message) {
        logger.info(String.format("Message sent -> %s", message));
        this.kafkaTemplate.send(Constants.TOPIC_NAME, message);
    }

}
