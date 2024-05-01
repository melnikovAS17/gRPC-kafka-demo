package ru.melnikov.proj.kafka.gRPC.grpc.service.dao.impl;

import org.springframework.stereotype.Service;
import ru.melnikov.proj.kafka.gRPC.grpc.service.dao.entity.ClientMessage;
import ru.melnikov.proj.kafka.gRPC.grpc.service.dao.repo.MessageSaverRepo;

@Service
public class MessageSaverService {
    private final MessageSaverRepo messageSaverRepo;

    public MessageSaverService(MessageSaverRepo messageSaverRepo) {
        this.messageSaverRepo = messageSaverRepo;
    }


    public void saveMes(ClientMessage clientMessage) {
        messageSaverRepo.save(clientMessage);
    }

}
