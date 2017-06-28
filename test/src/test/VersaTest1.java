package test;

import static org.junit.Assert.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.path.json.JsonPath;

public class VersaTest1 {

	static Logger logger = LogManager.getLogger(VersaTest1.class.getName());

	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "https://1.93.67.17:9182/api";
		RestAssured.port = 9182;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.authentication = basic("TestWH", "admin@TestWH-123");
		// RestAssured.requestSpecification=accept("application/json");
		// RestAssured.with().accept("application/json");
		logger.info("我是info信息");
		logger.error("我是error信息");
	}

	@After
	public void tearDown() throws Exception {
	}

	// @Ignore("忽略")
	@Test
	public void VerifyResponseCode() {
		// given().auth().basic("TestWH", "admin@TestWH-123")
		// .header("Accept", "application/json")
		// .get("/operational/appliances/Branch1-Hub-WH-DEV/live-status/orgs/org/Customer1/sd-wan/sla-monitor/path/metrics/last")
		// .then().body("title", equalTo("满月之夜白鲸现"));

		// Response response = given().auth().basic("TestWH",
		// "admin@TestWH-123")
		// .header("Accept", "application/json")
		// .get("/operational/appliances/Branch1-Hub-WH-DEV/live-status/orgs/org/Customer1/sd-wan/sla-monitor/path/metrics/last");

		Response response = given().header("Accept", "application/json").get(
				"/operational/appliances/Branch1-Hub-WH-DEV/live-status/orgs/org/Customer1/sd-wan/sla-monitor/path/metrics/last");

		int responsecode = response.statusCode();
		logger.info("Status Code: "+responsecode);
		assertThat(responsecode, equalTo(200));

		long timeInMs = response.time();

	}

	// @Ignore
	@Test(timeout = 50)
	public void VerifyHeader() {
		Response response = given().header("Accept", "application/json").get(
				"/operational/appliances/Branch1-Hub-WH-DEV/live-status/orgs/org/Customer1/sd-wan/sla-monitor/path/metrics/last");

		String headerValue = response.header("Content-Type");
		logger.info("Content-Type: "+headerValue);
		assertThat(headerValue, equalTo("application/vnd.yang.data+json"));
		
	}

	@Test
	public void VerifyBodyContent() {
		Response response = given().header("Accept", "application/json").get(
				"/operational/appliances/Branch1-Hub-WH-DEV/live-status/orgs/org/Customer1/sd-wan/sla-monitor/path/metrics/last");

		// String json = response.asString();
		// System.out.println(response.asString());
		int actualValue = JsonPath.from(response.getBody().asString()).getInt("last[0].path-handle");
		logger.info("Response : "+response.getBody().asString());
		logger.info("Node last[0].path-handle: "+actualValue);
		assertThat(actualValue, equalTo(6689024));
	}

}
