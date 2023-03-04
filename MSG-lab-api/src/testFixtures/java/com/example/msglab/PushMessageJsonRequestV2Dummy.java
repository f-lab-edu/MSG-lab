package com.example.msglab;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;

import lombok.Getter;

public class PushMessageJsonRequestV2Dummy {
    private static final Charset charset = StandardCharsets.UTF_8;
    @Getter
    private static String correctValue;

    static {
        final ClassPathResource classPathResource = new ClassPathResource("/PushMessageRequestV2Json");
        try (InputStream in = classPathResource.getInputStream()) {
            final byte[] bytes = in.readAllBytes();
            final ByteBuffer buffer = ByteBuffer.wrap(bytes);
            correctValue = charset.decode(buffer).toString();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
