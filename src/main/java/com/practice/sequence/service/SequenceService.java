package com.practice.sequence.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.practice.sequence.dto.RequestNumberVO;
import com.practice.sequence.dto.RespTaskSequenceVO;

@Service
public class SequenceService {

	synchronized public String calculateSequence(RequestNumberVO reqNumber) {
		// calculate sequence/ generate result
		Long goal = Long.valueOf(reqNumber.getGoal());
		Long step = Long.valueOf(reqNumber.getStep());
		List<Long> listSequence = new ArrayList<>();
		
		if(goal < step || step <= 0) {
			return null;
		}
		while (goal >= 0) {
			listSequence.add(goal);
			goal -= step;
		}
		return listSequence.toString().replace("[", "").replace("]", "");
	}

	public String generateSequence(RequestNumberVO requestNumber, Map<String, RespTaskSequenceVO> sequences) {
		if (!requestNumber.getGoal().isEmpty() && !requestNumber.getStep().isEmpty()) {

			RespTaskSequenceVO respTaskSequence = new RespTaskSequenceVO();
			List<String> listResultSequence = new ArrayList<>();

			respTaskSequence.setStatus(Status.inProgress.statusLable);
			try {
				String result = calculateSequence(requestNumber);
				if(result == null) {
					throw new IllegalArgumentException("Unable to calculate sequence");
				}
				listResultSequence.add(result);
				respTaskSequence.setResult(listResultSequence);
				respTaskSequence.setStatus(Status.success.statusLable);
			} catch (Exception e) {
				respTaskSequence.setStatus(Status.error.statusLable);
				return "Error Occured, Please provide Valid Goal and Step";
			}

			// generate UUID
			UUID uniqueTaskId = UUID.randomUUID();
			// put in Map<UUID, reqNumber
			sequences.put(uniqueTaskId.toString(), respTaskSequence);
			return uniqueTaskId.toString();
		}

		return new IllegalArgumentException("Cannot generate a sequence using given arguments").getLocalizedMessage();
	}

	public String bulkGenerateSequence(List<RequestNumberVO> reqNumberList, Map<String, RespTaskSequenceVO> sequences) {
		if (reqNumberList != null) {
			RespTaskSequenceVO respTaskSequence = new RespTaskSequenceVO();
			List<String> listResultSequence = new ArrayList<>();
			for (RequestNumberVO reqNumber : reqNumberList) {
				if (!reqNumber.getGoal().isEmpty() && !reqNumber.getStep().isEmpty()) {

					respTaskSequence.setStatus(Status.inProgress.statusLable);
					try {
						String result = calculateSequence(reqNumber); // calc sequence
						if(result == null) {
							throw new IllegalArgumentException("Unable to calculate sequence");
						}
						listResultSequence.add(result);
						respTaskSequence.setStatus(Status.success.statusLable);
					} catch (Exception e) {
						respTaskSequence.setStatus(Status.error.statusLable);
						return "Error Occured, Please provide Valid Goal and Step";
					}
				}
			}
			respTaskSequence.setResult(listResultSequence);
			// generate UUID UUID
			UUID uniqueTaskId = UUID.randomUUID(); // put in Map<UUID, respSequence>
			sequences.put(uniqueTaskId.toString(), respTaskSequence);
			return uniqueTaskId.toString();
		}

		return new IllegalArgumentException("Cannot generate a sequence using given arguments").getLocalizedMessage();
	}

	public String getTaskStatus(String id, Map<String, RespTaskSequenceVO> sequences) {
		if (!id.isEmpty()) {
			RespTaskSequenceVO respTaskSequence = sequences.get(id);
			return respTaskSequence != null ? respTaskSequence.getStatus()
					: "Unable to Find the number sequence in Memory";
		}
		return new IllegalArgumentException("UUID is required to get task status").getLocalizedMessage();
	}

	public List<String> getTaskResults(String id, String action, Map<String, RespTaskSequenceVO> sequences) {
		if (!id.isEmpty() && !action.isEmpty()) {
			if (action.equalsIgnoreCase("get_numlist")) {
				RespTaskSequenceVO respTaskSequence = sequences.get(id);
				return respTaskSequence != null ? respTaskSequence.getResult() : Collections.emptyList();
			}
		}
		return Collections.emptyList(); // return an empty List is place of null, if no result is found
	}

	// Status Enum
	public enum Status {
		success("SUCCESS"), inProgress("IN_PROGRESS"), error("ERROR");

		public final String statusLable;

		private Status(String lable) {
			this.statusLable = lable;
		}
	}
}
