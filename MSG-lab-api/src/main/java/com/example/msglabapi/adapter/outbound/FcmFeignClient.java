package com.example.msglabapi.adapter.outbound;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "fcm", url = "https://fcm.googleapis.com")
public interface FcmFeignClient {

    @PostMapping(path = "/fcm/send")
    void send(MessageRequestFcmV1 message);
}
