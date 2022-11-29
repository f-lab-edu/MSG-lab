package com.example.msglab.application;

import com.example.msglab.domain.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final ObjectMapper mapper = new ObjectMapper();
    public String send(Message message) {
        try {
            String data = mapper.writeValueAsString(message);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        // todo(hun) : 변환한 json string을 메세지 발송기에 전달하기
        return "-1";
    }
}
