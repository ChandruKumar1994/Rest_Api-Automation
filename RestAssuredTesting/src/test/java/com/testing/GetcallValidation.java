package com.testing;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetcallValidation {

	@Test
	public void getReg(){
		//given().when().get("https://api.dictionaryapi.dev/api/v2/entries/en/hello").then().assertThat().statusCode(200);	
		RestAssured.baseURI = "https://api.dictionaryapi.dev/api/v2/entries/en/";
		String URI = RestAssured.baseURI;
		Response res1 = RestAssured.given().when().get(URI + "friend");
		System.out.println("Response status is "+ res1.getStatusCode());

		if (res1.getStatusCode() == 200) {		
			res1.then().assertThat().body(matchesJsonSchemaInClasspath("testSchema.json"));
			System.out.println("Schema Validation is successful");
			res1.prettyPrint();
		}
		else{
			System.out.println(res1.getStatusCode());
			Assert.fail("Please give the correct value");
			System.out.println("The Master");
		}


	}

}
