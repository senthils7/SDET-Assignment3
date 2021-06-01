package IBM.RestAssured_Assignment3;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Excelsheet.Inputsfromexcel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredTestCase1 {
	
	@Test(enabled = true, dataProvider = "datafromexcel")
	public void jsonbody(String name1, String name2, String name3) {
		RestAssured.baseURI="http://localhost:3000/";
		
		JSONObject obj = new JSONObject();
		JSONObject category = new JSONObject();
		JSONObject tags = new JSONObject();

		
		
		obj.put("name", name1);
		obj.put("id", 0);
		obj.put("status", "xyz");
		category.put("id", 0);
		category.put("name", name2);
		tags.put("id", 0);
		tags.put("name", name3);
		
		//Adding category and tags into main obj body
		obj.put("category", category);
		obj.put("tags", tags);
		
		//JSON Array body
		JSONArray arraybody = new JSONArray();
		arraybody.add("String");
		
		obj.put("photoUrls", arraybody);
		
		System.out.println(obj);
		
		System.out.println(obj);
		Response res =given()
				.body(obj.toJSONString())
				.headers("content-type", "Application/JSON")
		.when()
		.post("students")
		.then().log().all().statusCode(201).extract().response();
		
		int statusCode = res.getStatusCode();
		Assert.assertEquals(201, statusCode);

	}	

	@DataProvider(name = "datafromexcel")
	public Object[][] exceldata() throws IOException {
		Object[][] data = Inputsfromexcel.gettestdata();

		return data;
	}

	
	

}
