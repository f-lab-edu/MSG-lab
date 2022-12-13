package com.example.msglab.adapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/")
    public String index() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        return "hello "+ip;
    }
}
