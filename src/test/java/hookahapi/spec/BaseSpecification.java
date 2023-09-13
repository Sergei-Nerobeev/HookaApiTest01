package hookahapi.spec;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.basePath;

public class BaseSpecification {
public static RequestSpecification baseSpecification(){

	Dotenv dotenv = Dotenv.load();
	return new RequestSpecBuilder()
		.setBaseUri(dotenv.get("LOCAL_URL"))
		.setBasePath(basePath)
		.setContentType(ContentType.JSON)
		.log(LogDetail.ALL)
		.build();
}
}
