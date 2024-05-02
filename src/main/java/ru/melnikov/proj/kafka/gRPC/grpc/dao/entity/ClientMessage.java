package ru.melnikov.proj.kafka.gRPC.grpc.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "messages")
public class ClientMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Long messageId;

    @Column(name = "message")
    private String message;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

}
