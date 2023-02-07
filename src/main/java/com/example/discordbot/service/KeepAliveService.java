package com.example.discordbot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KeepAliveService {

    @Scheduled(fixedRate = 1 * 1000 * 60) // 1m
    public void reportCurrentTime() {
        System.out.println(System.currentTimeMillis());
    }
}
