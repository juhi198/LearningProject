package com.example.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	
	
	public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean isSuccess,String message, Object responseObj) {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(ConstantUtility.TIME_STAMP, System.currentTimeMillis());
			map.put(ConstantUtility.STATUS, status.value());
			map.put("isSuccess", isSuccess);
			map.put(ConstantUtility.MESSAGE, message);
			map.put("data", responseObj);

			return new ResponseEntity<>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put(ConstantUtility.TIME_STAMP, System.currentTimeMillis());
			map.put(ConstantUtility.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put(ConstantUtility.ISSUCCESS,false);
			map.put(ConstantUtility.MESSAGE, e.getMessage());
			map.put("data", null);
			return new ResponseEntity<>(map,status);
		}
	}	
}
