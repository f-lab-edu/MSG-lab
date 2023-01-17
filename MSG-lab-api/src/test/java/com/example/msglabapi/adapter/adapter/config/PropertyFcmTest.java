package com.example.msglabapi.adapter.adapter.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.msglabapi.adapter.config.PropertyFcm;

@SpringBootTest
class PropertyFcmTest {

    @Autowired
    private PropertyFcm propertyFcm;

    @Test
    @DisplayName("propertyFcm 올바르게 바인딩되었는지 테스트")
    void test1() {
        Assertions.assertNotNull(propertyFcm.getAuth());
        Assertions.assertNotNull(propertyFcm.getUrl());
    }
}
