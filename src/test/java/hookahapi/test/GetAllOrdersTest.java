package hookahapi.test;

import hookahapi.dto.UserDto;
import hookahapi.generator.OrderGen;
import hookahapi.generator.PlaceGen;
import hookahapi.generator.UserGen;
import hookahapi.model.responce.PlaceResModel;
import hookahapi.spec.BaseSpec;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GetAllOrdersTest {

		Dotenv dotenv = Dotenv.load();
		UserDto user = UserGen.getInstance().createUser("OWNER");
		PlaceGen place = new PlaceGen();
		String token = user.getAuthToken();
		long userId = user.getUser().getId();
		PlaceResModel prm = place.createPlace(token, dotenv);
		long currentPlaceId = prm.getId();
		OrderGen order = new OrderGen();

		@Test
		public void getAllOrdersByCurrentIdPlace(){

				order.createOrder(currentPlaceId,userId,token,dotenv);
				given()
						.spec(BaseSpec.baseSpec())
						.header("Authorization", "Bearer " + token)
						.when()
						.get(dotenv.get("ORDER_GET_ALL") + currentPlaceId)
						.then()
						.log().all()
						.assertThat()
						.statusCode(HttpStatus.SC_OK);
		}

}
