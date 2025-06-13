package com.algorithmsolutionproject.algorithmsolution.service;

import com.algorithmsolutionproject.algorithmsolution.config.RabbitMQConfig;
import com.algorithmsolutionproject.algorithmsolution.dto.judge.JudgeRequest;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JudgeService {
    private final CodeService codeService;

    @RabbitListener(queues = RabbitMQConfig.JUDGE_QUEUE, ackMode = "MANUAL", containerFactory = "rabbitListenerContainerFactory")
    public void receiveJudgeRequest(JudgeRequest request, Channel channel, Message message) {
        long tag = message.getMessageProperties().getDeliveryTag();
        log.info("채점 요청: {}", request);

        try {
            codeService.processJudge(request);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("채점 도중 오류 발생", e);
            try {
                channel.basicNack(tag, false, true);
            } catch (IOException nackEx) {
                log.error("Nack 전송 실패", nackEx);
            }
        }

    }
}
