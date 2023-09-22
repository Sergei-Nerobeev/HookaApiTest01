package hookahapi.generator;

import hookahapi.dto.PlaceDto;
import hookahapi.model.responce.AddressResModel;
import hookahapi.model.responce.PlaceResModel;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class PlaceGen {

public void createPlace(String token, Dotenv dotenv)
{
	AddressResModel address = new AddressResModel();
	address.setCountry("USA");
	address.setAddress("LA");
	address.setLat(1.0);
	address.setLng(2.0);

	PlaceResModel placeResModel = new PlaceResModel();

	placeResModel.setName("OWNER signature");
	placeResModel.setAddress(address);

  given()
		.log().all()
		.header("Authorization", "Bearer " + token)
		.contentType(ContentType.JSON)
		.body(placeResModel)
		.when()
		.post(dotenv.get("PLACE_CREATE"))
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.extract().response().as(PlaceResModel.class);

	}

}
