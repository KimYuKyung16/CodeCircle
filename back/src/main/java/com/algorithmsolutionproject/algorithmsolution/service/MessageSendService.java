package com.algorithmsolutionproject.algorithmsolution.service;

import com.algorithmsolutionproject.algorithmsolution.config.RabbitMQConfig;
import com.algorithmsolutionproject.algorithmsolution.dto.judge.JudgeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageSendService {
    private final RabbitTemplate rabbitTemplate;

    public void sendJudgeRequest(JudgeRequest request) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.JUDGE_QUEUE, request);
    }
}
