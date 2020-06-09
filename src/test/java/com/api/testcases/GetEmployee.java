package com.api.testcases;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.client.RestClient;
import com.api.constant.Constant;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetEmployee {
	Logger log = Logger.getLogger(GetEmployee.class);

	String baseURI = "http://dummy.restapiexample.com";
	String basePath = "/api/v1/employees";

	@Test
	public void createEmployee() {
		log.info(
				"****************************** Starting test cases execution GET Request *****************************************");

		log.info("entering GET API URL");
		Response response = RestClient.doGet("JSON", baseURI, basePath, false);

		log.info("Verifying response");
		Assert.assertEquals(Constant.HTTP_STATUS_CODE_200, response.statusCode());

		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());

		JsonPath js = RestClient.getJsonPath(response);
		ArrayList results = js.get("data");

		log.info("tatal employees countis ::" + results.size());

		log.info("verifying first user");

		System.out.println(results.get(1));

		log.info("****************************** ending test case *****************************************");
	}
}
