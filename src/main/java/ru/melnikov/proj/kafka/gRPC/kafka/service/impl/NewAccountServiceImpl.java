package ru.melnikov.proj.kafka.gRPC.kafka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.melnikov.demo.gRPCtest.client.NewAccountEvent;
import ru.melnikov.proj.kafka.gRPC.kafka.service.NewAccountService;
import ru.melnikov.proj.kafka.gRPC.kafka.dto.request.NewAccountDto;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class NewAccountServiceImpl implements NewAccountService {

    private KafkaTemplate<String, NewAccountEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public NewAccountServiceImpl(KafkaTemplate<String, NewAccountEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String addNewAccount(NewAccountDto newAccountDto) {


        String accountId = UUID.randomUUID().toString();
        NewAccountEvent accountEvent = new NewAccountEvent(accountId, newAccountDto.getName(),newAccountDto.getAge(),
                newAccountDto.getGreetingMessage());
        // синхронная передача сообщ
        try {
            //SendResult нужен для получения ответа от брокера (получил ли он сообщ и что получил)
            SendResult<String, NewAccountEvent> result = kafkaTemplate.send("account-list", accountId, accountEvent).get();
            logger.info("Return: {}", result.getRecordMetadata().topic());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return accountId;
    }
}
