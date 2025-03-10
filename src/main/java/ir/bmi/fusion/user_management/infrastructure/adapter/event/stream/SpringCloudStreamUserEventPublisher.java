package ir.bmi.fusion.user_management.infrastructure.adapter.event.stream;

import ir.bmi.fusion.user_management.domain.port.outbound.UserEventPublisher;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.dto.UserEvent;
import ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.mapper.UserEventMapper;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SpringCloudStreamUserEventPublisher implements UserEventPublisher {
    /**
     * use stream bridge and explicit binding definition to integrate EDA with other frameworks
     */
    private final StreamBridge streamBridge;
    private final UserEventMapper userEventMapper;

    public SpringCloudStreamUserEventPublisher(StreamBridge streamBridge, UserEventMapper userEventMapper) {
        this.streamBridge = streamBridge;
        this.userEventMapper = userEventMapper;
    }

    @Override
    public void userCreated(UserValue userValue) {
        Message<UserEvent> message = MessageBuilder.withPayload(userEventMapper.toEvent(userValue))
                .setHeader(KafkaHeaders.KEY, String.valueOf(userValue.id()))
                .setHeader(MessageHeaders.CONTENT_TYPE, "application/json")
                .build();
        streamBridge.send("userCreated",message);
    }
}
