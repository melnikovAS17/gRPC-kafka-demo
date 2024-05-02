package ru.melnikov.proj.kafka.gRPC.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.aston.lab.example.grpc.TestHelloServer;
import ru.aston.lab.example.grpc.HandshakeServiceGrpc;
import ru.melnikov.proj.kafka.gRPC.grpc.dao.entity.ClientMessage;
import ru.melnikov.proj.kafka.gRPC.grpc.dao.services.MessageSaverService;


@GrpcService
public class HandshakeServiceImpl extends HandshakeServiceGrpc.HandshakeServiceImplBase {


    private final MessageSaverService messageSaverService;

    public HandshakeServiceImpl(MessageSaverService messageSaverService) {
        this.messageSaverService = messageSaverService;
    }

    @Override
    public void exchangeMessages(TestHelloServer.HandshakeRequest request,
                                 StreamObserver<TestHelloServer.HandshakeResponse> responseObserver) {
            System.out.println(request);
            ClientMessage clientMessage = ClientMessage.builder()
                .message(request.getMessage())
                .uuid(request.getUuid())
                .name(request.getName())
                .build();


            TestHelloServer.HandshakeResponse response = TestHelloServer.HandshakeResponse
                    .newBuilder()
                    .setMessage("Received message: "+ request.getMessage() + " name: " + request.getName())
                    .build();

            System.out.println(response);
            messageSaverService.saveMes(clientMessage);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

}
