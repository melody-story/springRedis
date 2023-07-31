package com.example.springRedis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ChatService implements MessageListener {

    @Autowired
    @Qualifier("redisContainer")
    private RedisMessageListenerContainer container;

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    // 사용자의 입력을 받음
    public void enterCharRoom(String chatRoomName) {
        container.addMessageListener(this, new ChannelTopic(chatRoomName));

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("q")) {
                System.out.println("Quit...");
                break;
            }

            redisTemplate.convertAndSend(chatRoomName, line); //  메시지 전송
        }
        container.removeMessageListener(this);
    }

    @Override // 받은 메시지를 표시
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("message = " + message.toString());
    }
}
