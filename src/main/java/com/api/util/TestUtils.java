package com.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	/**
	 * this method is used to convert POJO to JSON String
	 *
	 * @param obj
	 * @return
	 */

	public static String getSerializedJson(Object obj) {
		String jsonString = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;

	}
}
