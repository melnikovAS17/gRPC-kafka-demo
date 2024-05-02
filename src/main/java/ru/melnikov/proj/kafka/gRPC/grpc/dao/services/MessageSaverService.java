package ru.melnikov.proj.kafka.gRPC.grpc.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.melnikov.proj.kafka.gRPC.grpc.dao.entity.ClientMessage;
import ru.melnikov.proj.kafka.gRPC.grpc.dao.repositories.MessageSaverRepo;

@Service
public class MessageSaverService {

    @Autowired
    private MessageSaverRepo messageSaverRepo;


    public void saveMes(ClientMessage clientMessage) {
        messageSaverRepo.save(clientMessage);
    }

}
