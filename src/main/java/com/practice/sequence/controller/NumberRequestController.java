package com.practice.sequence.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.sequence.dto.RequestNumberVO;
import com.practice.sequence.dto.RespTaskSequenceVO;
import com.practice.sequence.service.SequenceService;

@RestController
@RequestMapping("/api")
public class NumberRequestController {

	@Autowired
	private SequenceService seqService;

	// ConcurrentHashMap will serve us as a memory to hold all our tasks and sequences
	private ConcurrentHashMap<String, RespTaskSequenceVO> sequences = new ConcurrentHashMap<>();

	@PostMapping("/generate")
	public String generateSequence(@RequestBody RequestNumberVO requestNumber) {
		return seqService.generateSequence(requestNumber, sequences);
		
	}

	@PostMapping("/bulkGenerate")
	public String bulkGenerateSequence(@RequestBody List<RequestNumberVO> reqNumberList) {
		return seqService.bulkGenerateSequence(reqNumberList, sequences);
	}

	@GetMapping("/tasks/{id}/status")
	public String getTaskStatus(@RequestParam String id) {
		return seqService.getTaskStatus(id, sequences);
	}

	@GetMapping("/tasks/{id}")
	public List<String> getTaskResults(@RequestParam String id, @RequestParam(value = "action") String action) {
		return seqService.getTaskResults(id, action, sequences);
	}

	@GetMapping("/getAll")
	public Map<String, RespTaskSequenceVO> getAllSequences() {
		return sequences;
	}
}
