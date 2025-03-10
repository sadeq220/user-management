package ir.bmi.fusion.user_management.infrastructure.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.cloud.stream.binder.kafka.support.ProducerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public ProducerConfigCustomizer kafkaProducerCustomizer(){
        return (producerProperties, bindingName, destination) -> {
            producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
            // producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");// spring cloud stream converts message body to byte[] already
            producerProperties.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,120_000);
            producerProperties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,true);
            producerProperties.put(ProducerConfig.ACKS_CONFIG,"all");
            producerProperties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,15_000);//kafka.timeout , default is 30s , wait time for a reply from the broker
        };
    }
}
