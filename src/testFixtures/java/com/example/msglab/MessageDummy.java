package com.example.msglab;

import com.example.msglab.domain.Message;
import com.example.msglab.domain.Notification;

public class MessageDummy extends Message{
    public static Message message = new Message("/topics/news", new Notification("Breaking News", "asdad"));

    public MessageDummy(String to, Notification notification) {
        super(to, notification);
    }
}
