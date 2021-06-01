package IBM.RestAssured_Assignment3;
import java.io.FileInputStream;
import java.io.IOException;
import static io.restassured.RestAssured.given;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Testcase2 
{
	@Test
	public void soap() throws IOException {

		RestAssured.baseURI ="http://www.dneonline.com";

		FileInputStream fis = new FileInputStream("/Users/senthils/eclipse-workspace/RestAssured_Assignment3/Payload/MultiplyTest.xml");

		Response res = given() 
							.headers("content-type","text/xml") 
							.body(IOUtils.toString(fis,"UTF-8")).
					  when() 
					  		.post("/calculator.asmx"). 
					  then().log().all().extract().response();
		
		Assert.assertEquals(200, res.statusCode());
		System.out.println("request created successfully");
	}

}
