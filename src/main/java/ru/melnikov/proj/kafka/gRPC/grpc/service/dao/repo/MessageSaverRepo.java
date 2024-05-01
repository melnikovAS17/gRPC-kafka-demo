package ru.melnikov.proj.kafka.gRPC.grpc.service.dao.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.melnikov.proj.kafka.gRPC.grpc.service.dao.entity.ClientMessage;

@Repository
public interface MessageSaverRepo extends JpaRepository<ClientMessage, Long> {


}
