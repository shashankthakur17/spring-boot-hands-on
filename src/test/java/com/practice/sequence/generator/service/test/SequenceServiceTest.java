package com.practice.sequence.generator.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;

import com.practice.sequence.dto.RequestNumberVO;
import com.practice.sequence.dto.RespTaskSequenceVO;
import com.practice.sequence.service.SequenceService;

public class SequenceServiceTest {

	
	private SequenceService sequenceService = new SequenceService();

	ConcurrentHashMap<String, RespTaskSequenceVO> sequences = new ConcurrentHashMap<>();

	@Test
	public void testCalculateSequence() {
		RequestNumberVO requestNum = new RequestNumberVO();
		requestNum.setGoal("80");
		requestNum.setStep("13");
		String result = sequenceService.calculateSequence(requestNum);
		assertNotNull(result);
	}

	@Test
	public void testGenerateSequence() {
		RequestNumberVO requestNum = new RequestNumberVO();
		requestNum.setGoal("100");
		requestNum.setStep("17");
		String uUid = sequenceService.generateSequence(requestNum, sequences);
		assertNotNull(UUID.fromString(uUid));

	}
	
	@Test
	public void testBulkGenerateSequence() {
		RequestNumberVO requestNum1 = new RequestNumberVO();
		requestNum1.setGoal("50");
		requestNum1.setStep("6");
		
		RequestNumberVO requestNum2 = new RequestNumberVO();
		requestNum2.setGoal("100");
		requestNum2.setStep("17");
		
		RequestNumberVO requestNum3 = new RequestNumberVO();
		requestNum3.setGoal("75");
		requestNum3.setStep("10");
		
		List<RequestNumberVO> listReqNumVO = new ArrayList<>();
		listReqNumVO.add(requestNum1);
		listReqNumVO.add(requestNum2);
		listReqNumVO.add(requestNum3);
		
		String uUid = sequenceService.bulkGenerateSequence(listReqNumVO, sequences);
		assertNotNull(UUID.fromString(uUid));
	}

	@Test
	public void testGetTaskStatus() {
		List<String> listIds = new ArrayList<>(sequences.keySet());
		for(String uid: listIds) {
			assertEquals("Success",
					sequenceService.getTaskStatus(uid, sequences));
		}
	}
	
	@Test
	public void testGetTaskResults() {
		List<String> listIds = new ArrayList<>(sequences.keySet());
		for(String uid: listIds) {
			assertNotNull(sequenceService.getTaskResults(uid, "get_numlist", sequences));
		}
	}
	
}
