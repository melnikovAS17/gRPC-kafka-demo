package ru.melnikov.proj.kafka.gRPC.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.melnikov.demo.gRPCtest.client.NewAccountEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
// можно настроить кафку таким образом
public class KafkaConfig {

    //сетим бутстрап сервер
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;
    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;
    @Value("${spring.kafka.producer.acks}")
    private String acks;
    //карта с конфигами producer
    Map<String, Object> producerConfigs() {
        Map<String, Object> conf = new HashMap<>();
        conf.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        conf.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        conf.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        // не ждёт ответ от брокеров (если есть реплики, в моей кафке реплик нет)
        conf.put(ProducerConfig.ACKS_CONFIG, acks);
        //добавляем новые проперти неописанные в application
        //retries - кол-во попыток, которое сделает producer, если с первого раза отдать сообщение не получилось
        conf.put(ProducerConfig.RETRIES_CONFIG, 0);
        // через сколько мс будет отправляться повторно сообщ
       /* conf.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG,0); */
        // сколько времени будет тратиться на попытки достучаться до брокера в мс (здесь 1 минута)
       /*  conf.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,60000); */
        // сколько времени будем накапливтаь сообщения перед отправкой мс (здесь будет отправляться по 1 сообщению)
        conf.put(ProducerConfig.LINGER_MS_CONFIG, 0);
        // время сколько будем ждать ответа от брокера/ов в мс
        /* conf.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,30000); */
        // если при доставке сообщения в брокер не получили ответа от него, после ретрая
        //не будет создана копия сообщения если оно там уже есть
        /* conf.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,true); */
        return conf;
    }
    //bean принимает наши конфиги
    @Bean
    ProducerFactory<String, NewAccountEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String,NewAccountEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // Создание нового топика
    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("account-list")
                //кол-во партиций
                .partitions(3)
                //кол-во реплик
                .replicas(1)
                //мин кол-во серверов в синхроне(т е иметь те сообщ, что и лидер) с лидером(желательно указ-ть на один брокер меньше)
                // означение ниже 1 -> ошибка
                .configs(Map.of("min.insync.replicas","1"))
                .build();
    }
}
