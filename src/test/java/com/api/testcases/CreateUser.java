package com.api.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.client.RestClient;
import com.api.constant.Constant;
import com.api.pojo.Employee;
import com.api.util.ExcelUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUser {
	Logger log = Logger.getLogger(CreateUser.class);

	String baseURI = "http://dummy.restapiexample.com";
	String basePath = "/api/v1/create";

	@DataProvider
	public Object[][] getEmployeeData() {
		Object employeeData[][] = ExcelUtil.getTestData("Sheet1");
		return employeeData;
	}

	@Test(dataProvider = "getEmployeeData")
	public void getEmployeeDetails(String name, String salary, String age) {
		log.info(
				"****************************** Starting test cases execution POST Request *****************************************");

		log.info("entering POST API URL");

		log.info("reading data from excel file");
		Employee employee = new Employee(name, salary, age);

		Response response = RestClient.doPost("JSON", baseURI, basePath, false, employee);

		System.out.println(response.statusCode());

		Assert.assertEquals(Constant.HTTP_STATUS_CODE_200, response.statusCode());

		System.out.println(response.prettyPrint());
		JsonPath js = response.jsonPath();

		String getEmployeeName = js.get("data.name");
		System.out.println(getEmployeeName);

		log.info("get employee details" + getEmployeeName);

		log.info("****************************** ending test case *****************************************");
	}

}
