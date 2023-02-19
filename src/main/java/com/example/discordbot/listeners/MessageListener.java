package com.example.discordbot.listeners;

import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

public abstract class MessageListener {

    private String author = "UNKNOWN";

    @Value("${spring.datasource.alertChannelId}")
    private String alertChannelId;

    public Mono<Void> processMessage(final Message eventMessage) {

        if (eventMessage.getData().channelId().toString().equals(alertChannelId)) {
            return Mono.just(eventMessage)
                    .filter(message -> message.getAuthor()
                            .map(user -> !user.isBot())
                            .orElse(false))
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage("@everyone"))
                    .then();
        }

        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor()
                        .map(user -> !user.isBot())
                        .orElse(false))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("사용하지 않는 기능입니다."))
                .then();
    }
}
