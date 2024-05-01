package ru.melnikov.proj.kafka.gRPC.kafka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.melnikov.proj.kafka.gRPC.kafka.service.NewAccountService;
import ru.melnikov.proj.kafka.gRPC.kafka.dto.request.NewAccountDto;

@RestController
public class DemoNewAccountController {

    private final NewAccountService newAccountService;

    public DemoNewAccountController(NewAccountService newAccountService) {
        this.newAccountService = newAccountService;
    }

    @PostMapping("/account/new")
    public ResponseEntity<String> setNewProduct(@RequestBody NewAccountDto newAccountDto) {
        String accountId = newAccountService.addNewAccount(newAccountDto);
        return ResponseEntity.status(HttpStatus.OK).body(accountId);
    }
}
