package com.example.msglab.application;

import com.example.msglab.domain.Message;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final ObjectMapper mapper = new ObjectMapper();
    public String send(Message message) throws JsonProcessingException {
        // todo(hun) : 변환하려는 객체의 필드가 private이면 올바르게 매핑 못해주는 경우가 있습니다.
        //  이런 외부 라이브러리의 에러는 어디서 테스트해야 할까요?(아래와 같이 설정해줘야 올바르게 매핑됩니다.)
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

        // todo(hun) : jsonException을 여기서 처리하면 좋을까요?
        String data = mapper.writeValueAsString(message);

        // todo(hun) : 변환한 json string을 메세지 발송기에 전달하기
        return "-1";
    }
}
