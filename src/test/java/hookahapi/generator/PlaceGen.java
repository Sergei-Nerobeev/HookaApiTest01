package hookahapi.generator;

import hookahapi.model.responce.AddressResModel;
import hookahapi.model.responce.PlaceResModel;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import lombok.Getter;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
@Getter
public class PlaceGen {

public PlaceResModel createPlace(String token, Dotenv dotenv)
{
	AddressResModel address = new AddressResModel();
	address.setCountry("USA");
	address.setAddress("LA");
	address.setLat(1.0);
	address.setLng(2.0);

	PlaceResModel prm = new PlaceResModel();

	prm.setName("Some great place!");
	prm.setAddress(address);

  PlaceResModel placeResModel = given()
		.log().all()
		.header("Authorization", "Bearer " + token)
		.contentType(ContentType.JSON)
		.body(prm)
		.when()
		.post(dotenv.get("PLACE_CREATE"))
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.extract().response().as(PlaceResModel.class);

  long placeId = placeResModel.getId();
	return placeResModel;
}

}
