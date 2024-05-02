package ru.melnikov.proj.kafka.gRPC.grpc.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.melnikov.proj.kafka.gRPC.grpc.dao.entity.ClientMessage;

@Component
public interface MessageSaverRepo extends JpaRepository<ClientMessage, Long> {


}
