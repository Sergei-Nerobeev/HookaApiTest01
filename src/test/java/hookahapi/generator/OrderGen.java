package hookahapi.generator;

import hookahapi.model.responce.order.OrderResModel;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import lombok.Data;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
@Data
public class OrderGen
{
public void createOrder(long idPlace, long idUser, String token, Dotenv dotenv){

	Map<String, Object> requestBody = new HashMap<>();
	Map<String, Long> placeId = new HashMap<>();
	placeId.put("id", idPlace);
	Map<String, Object> userId = new HashMap<>();
	userId.put("id", idUser);
	Map<String, Object> comment = new HashMap<>();
	comment.put("text", "Order is placed");

	requestBody.put("place_id", placeId);
	requestBody.put("user_id", userId);
	requestBody.put("order_time", " ");
	requestBody.put("comment", comment);
	requestBody.put("order_status", "NEW");

	OrderResModel orm = given()
		.log().all()
		.header("Authorization", "Bearer " + token)
		.contentType(ContentType.JSON)
		.body(requestBody)
		.when()
		.post(dotenv.get("ORDER_CREATE"))
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.extract().response().as(OrderResModel.class);


	}

public Long createOrderAndGetId(long idPlace, long idUser, String token, Dotenv dotenv){

				Map<String, Object> requestBody = new HashMap<>();
				Map<String, Long> placeId = new HashMap<>();
				placeId.put("id", idPlace);
				Map<String, Object> userId = new HashMap<>();
				userId.put("id", idUser);
				Map<String, Object> comment = new HashMap<>();
				comment.put("text", "Order is placed");

				requestBody.put("place_id", placeId);
				requestBody.put("user_id", userId);
				requestBody.put("order_time", " ");
				requestBody.put("comment", comment);
				requestBody.put("order_status", "NEW");

				OrderResModel orm = given()
						.log().all()
						.header("Authorization", "Bearer " + token)
						.contentType(ContentType.JSON)
						.body(requestBody)
						.when()
						.post(dotenv.get("ORDER_CREATE"))
						.then()
						.assertThat()
						.statusCode(HttpStatus.SC_OK)
						.extract().response().as(OrderResModel.class);

				return orm.getId();// просто получение айди заказа
		}
}
