package ru.melnikov.proj.kafka.gRPC.kafka.service;

import ru.melnikov.proj.kafka.gRPC.kafka.dto.request.NewAccountDto;

public interface NewAccountService {

    String addNewAccount(NewAccountDto newAccountDto);
}
