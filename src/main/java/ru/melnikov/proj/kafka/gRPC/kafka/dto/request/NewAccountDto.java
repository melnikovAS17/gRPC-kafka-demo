package ru.melnikov.proj.kafka.gRPC.kafka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewAccountDto {
    private String name;
    private Integer age;
    private String greetingMessage;

}
