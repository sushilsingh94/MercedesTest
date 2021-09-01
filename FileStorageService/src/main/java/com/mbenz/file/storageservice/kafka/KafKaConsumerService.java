package com.mbenz.file.storageservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbenz.file.storageservice.dto.KafkaUserRequest;
import com.mbenz.file.storageservice.dto.OperationType;
import com.mbenz.file.storageservice.service.UserService;
import com.mbenz.file.storageservice.utils.AESUtil;
import com.mbenz.file.storageservice.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.nio.charset.MalformedInputException;

@Service
public class KafKaConsumerService
{
    private final Logger logger = LoggerFactory.getLogger(KafKaConsumerService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = Constants.TOPIC_NAME,
            groupId = Constants.GROUP_ID)
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void consume(String message) {
        logger.info(String.format("Message recieved -> %s", message));
        try {
            KafkaUserRequest kafkaUserRequest = objectMapper.readValue(AESUtil.decrypt(message), KafkaUserRequest.class);
            logger.info(String.format("Message recieved after decryption -> %s", kafkaUserRequest));
            if(OperationType.UPDATE.equals(kafkaUserRequest.getOperationType())) {
                userService.updateUserDetails(kafkaUserRequest.getId(), kafkaUserRequest.getRequest(), kafkaUserRequest.getFileType());
            }else if(OperationType.CREATE.equals(kafkaUserRequest.getOperationType())){
                userService.saveUserDetails(kafkaUserRequest.getRequest(), kafkaUserRequest.getFileType());
            }else {
                throw new InvalidObjectException("Invalid input");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}