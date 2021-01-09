package com.practice.sequence.generator.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.sequence.controller.NumberRequestController;

@SpringBootTest
class SequencegeneratorApplicationTests {

	@Autowired
	private NumberRequestController controller;
	
	@Test
	void contextLoads() throws Exception{
		assertNotNull(controller);
	}

}
