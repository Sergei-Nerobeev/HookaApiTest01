package hookahapi.generator;

import hookahapi.dto.PlaceDto;
import hookahapi.model.responce.AddressResModel;
import hookahapi.model.responce.PlaceResModel;
import hookapi.DTO.PlaceDTO;
import hookapi.entity.order.response.Address;
import hookapi.entity.place.ResponseCreatePlace;
import hookapi.service.PlaceGenerator;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class PlaceGen {
public static PlaceGen placeGen;
public static PlaceGen getInstance() {

	if (placeGen == null) {
		placeGen = new PlaceGen();
	}
	return placeGen;
}

public PlaceDto createNewPlace(String token, Dotenv dotenv)
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
		.extract().response().as(ResponseCreatePlace.class);

	return new PlaceDto(placeResModel);

}

}
