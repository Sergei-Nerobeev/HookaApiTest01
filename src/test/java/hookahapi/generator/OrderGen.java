package hookahapi.generator;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.Data;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
@Data
public class OrderGen
{
public static OrderGen orderGen;
public OrderGen(){}
public static OrderGen getInstance() {
	if (orderGen == null) {
		orderGen = new OrderGen();
	}
	return orderGen;
}
public void createOrder(long idPlace, int idUser, String token, Dotenv dotenv){

	Map<String, Object> requestBody = new HashMap<>();
	Map<String, Object> placeId = new HashMap<>();
	placeId.put("id", idPlace);
	Map<String, Object> userId = new HashMap<>();
	userId.put("id", idUser);
	Map<String, Object> comment = new HashMap<>();
	comment.put("text", "Работает создание заказа");

	requestBody.put("place_id", placeId);
	requestBody.put("user_id", userId);
	requestBody.put("order_time", "2023-06-06T10:00:00");
	requestBody.put("comment", comment);
	requestBody.put("orderStatus", "NEW");

	ValidatableResponse resCreateOrder = given()
		.log().all()
		.header("Authorization", "Bearer " + token)
		.contentType(ContentType.JSON)
		.body(requestBody)
		.when()
		.post(dotenv.get("ORDER_CREATE"))
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);

	//new OrderDTO();

}


}
