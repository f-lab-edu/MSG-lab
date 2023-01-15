package com.example.msglab;

import com.example.msglabapi.domain.Message;
import com.example.msglabapi.domain.Notification;

public class MessageDummy extends Message {
    public static final Message message = new Message("/topics/news",
                                                      new Notification("Breaking News", "asdad"));
    public MessageDummy(String to, Notification notification) {
        super(to, notification);
    }
}
