package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class testapi {

	@Before
	public void setUp() throws Exception {
		// 指定 URL 和端口号
//		RestAssured.baseURI = "https://1.93.67.17:9182/api/config/aaa/authentication/users/user";
////		RestAssured.port = 80;

//		given().header("Authorization", "Basic RGV2V0g6YWRtaW5ARGV2V0gtMTIz");
//		given().header("Accept", "application/json");
		
		RestAssured.baseURI = "https://api.douban.com/v2/book";  
        RestAssured.port = 443; 
//        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());
		RestAssured.useRelaxedHTTPSValidation();
	}
	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		get("/1220562").then().body("title", equalTo("满月之夜白鲸现"));  
	}

}
