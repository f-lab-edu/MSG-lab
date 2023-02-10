package com.example.msglabapi.adapter.outbound;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import com.example.msglabapi.application.outbound.MessageClient;
import com.example.msglabapi.domain.Message;

import lombok.RequiredArgsConstructor;

import feign.FeignException.FeignClientException;

@Component
@RequiredArgsConstructor
public class MessageClientFcm implements MessageClient {

    private final FcmFeignClient fcmFeignClient;

    /**
     * 재요청 로직은 <a href="https://en.wikipedia.org/wiki/Packet_delay_variation">Packet delay variation</a>을 적용합니다.<br>
     * 이는 Jitter(지터)로 알려진 개념입니다.<br>
     * <br>
     * 적용 방식은 아래와 같습니다.<br>
     * Backoff(delay = 1000, multiplier = 1.2, maxDelay = 3000, random = true)이라면,<br>
     * 처음 요청은 1초를 기다립니다.<br>
     * 이후 요청은 이전 요청에 1.2를 곱한 기간, 여기서는 1.2초, 1초에서 1.2초 사이에 해당하는 시간을 기다립니다.<br>
     * 각 요청은 최대 3초까지 기다립니다.
     */
    @Override
    @Retryable(maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 1.2, maxDelay = 3000, random = true),
            value = FeignClientException.class)
    public void send(final Message message) {
        final MessageRequestFcmV1 messageRequestFCM = MessageRequestFcmV1.from(message);
        fcmFeignClient.send(messageRequestFCM);
    }

    @Recover
    public void recoverPost(final FeignClientException e) {
        // todo(hun): 현재 retryable 메소드의 리턴값이 void입니다. 적당한 리턴 클래스를 추가해야 합니다.
    }
}
