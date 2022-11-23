package com.example.msglab.adapter;

import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @PostMapping("/send")
    public String sendMsg(@RequestBody Message message) {
        // todo(hun): 수신한 메세지를 json형태로 다시 바꾸기
        // todo(hun): ObjectMapper, Gson 등의 라이브러리를 이용할 것 같습니다.
        // todo(hun): FCM에 json으로 변환한 메세지를 전송하기
        return "";
    }
}
