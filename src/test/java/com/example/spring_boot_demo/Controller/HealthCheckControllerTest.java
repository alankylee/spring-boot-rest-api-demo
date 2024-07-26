package com.example.spring_boot_demo.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring_boot_demo.constant.SwaggerExamples;
import com.example.spring_boot_demo.controller.HealthCheckController;
import com.example.spring_boot_demo.payload.GenericResponseEntity;
import com.google.gson.Gson;

@SpringBootTest
class HealthCheckControllerTest {

    @Autowired
    private HealthCheckController controller;

	@Test
	void checkResult() {
        var checkRespBody = controller.check().getBody();
		Assertions.assertNotNull(checkRespBody);
        Assertions.assertEquals(new Gson().fromJson(SwaggerExamples.HealthCheckExamples.CHECK_SUCCESS_RESPONSE, GenericResponseEntity.class), checkRespBody);
	}

}