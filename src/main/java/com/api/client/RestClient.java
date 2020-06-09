package com.api.client;



import com.api.util.TestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	// GET POST PUT DELETE

	public static Response doGet(String contentType, String baseURI, String basePath, boolean log) {
		RestClient.setBaseURI(baseURI);
		RequestSpecification request = RestClient.createRequest(contentType, log);
		return RestClient.getResponse("GET", request, basePath);
	}

	public static Response doPost(String contentType, String baseURI, String basePath, boolean log, Object obj) {
		RestClient.setBaseURI(baseURI);
		RequestSpecification request = RestClient.createRequest(contentType, log);
		String jsonPayload = TestUtils.getSerializedJson(obj);
		request.body(jsonPayload);
		return RestClient.getResponse("POST", request, basePath);
	}

	public static Response doPut(String contentType, String baseURI, String basePath, boolean log, Object obj) {

		RequestSpecification request = RestClient.createRequest(contentType, log);
		String jsonPayload = TestUtils.getSerializedJson(obj);
		request.body(jsonPayload);
		return RestClient.getResponse("PUT", request, basePath);

	}

	public static void setBaseURI(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	/**
	 *
	 * this method is used to create request
	 *
	 * @param contentType
	 * @param token
	 * @param log
	 * @return
	 */

	public static RequestSpecification createRequest(String contentType, boolean log) {
		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		if (contentType.equalsIgnoreCase("JSON'")) {
			request.contentType(ContentType.JSON);
		} else if (contentType.equalsIgnoreCase("JSON'")) {
			request.contentType(ContentType.XML);
		} else if (contentType.equalsIgnoreCase("JSON'")) {
			request.contentType(ContentType.TEXT);
		}

		return request;

	}

	public static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeAPI(httpMethod, request, basePath);
	}

	/**
	 * this method is used to execute the API on the basis of HTTP
	 * METHID(GET,POST,PUT,DELETE)
	 *
	 * @param httpMethod
	 * @param request
	 * @param basePath
	 * @return
	 */

	private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;
		default:
			System.out.println("Please pass the correct http method");
			break;

		}
		return response;
	}

	public static JsonPath getJsonPath(Response response) {
		return response.jsonPath();
	}
}
