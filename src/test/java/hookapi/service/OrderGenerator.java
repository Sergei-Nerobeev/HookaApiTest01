/*
package hookapi.service;

import hookapi.DTO.OrderDTO;
import hookapi.DTO.UserDTO;
import hookapi.entity.order.response.ResponseCreateOrder;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderGenerator {
//private PlaceGenerator place = new PlaceGenerator();
private ResponseCreateOrder order = new ResponseCreateOrder();
public OrderDTO createOrder(){

	Map<String, Object> requestBody = new HashMap<>();
	Map<String, Object> placeId = new HashMap<>();
	placeId.put("id", idPlace);
	Map<String, Object> userId = new HashMap<>();
	userId.put("id", responseCreateUser.getId());
	Map<String, Object> comment = new HashMap<>();
	comment.put("text", "Работает создание заказа");

	requestBody.put("place_id", placeId);
	requestBody.put("user_id", userId);
	requestBody.put("order_time", "2023-06-06T10:00:00");
	requestBody.put("comment", comment);
	requestBody.put("orderStatus", "NEW");

	Dotenv dotenv = Dotenv.load();
	UserDTO newOwner = UserGenerator.getInstance().createOwner();
	var token = newOwner.getAuthToken();

	ValidatableResponse validatableResponse = given()
		.log().all()
		.header("Authorization", "Bearer " + token)
		.contentType(ContentType.JSON)
		.body(requestBody)
		.when()
		.post(dotenv.get("ORDER_CREATE"))
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);

	return new OrderDTO(order);

}
}
*/
