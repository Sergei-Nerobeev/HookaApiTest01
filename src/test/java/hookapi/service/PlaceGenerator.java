package hookapi.service;

import hookapi.DTO.PlaceDTO;
import hookapi.entity.order.response.Address;
import hookapi.entity.place.ResponseCreatePlace;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import lombok.Data;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
@Data
public class PlaceGenerator {
public static PlaceGenerator placeGenerator;
private PlaceGenerator(){}
public static PlaceGenerator getInstance() {
	if (placeGenerator == null) {
		placeGenerator = new PlaceGenerator();
	}
	return placeGenerator;
}

public PlaceDTO createPlace(String token, Dotenv dotenv)
{
	Address address = new Address();
	address.setId("");
	address.setCountry("USA");
	address.setAddress("LA");
	address.setLat(1.0);
	address.setLng(2.0);
	address.setCreatedAt("");
	address.setUpdatedAt("");
	address.setDeletedAt("");

	ResponseCreatePlace place = new ResponseCreatePlace();

	place.setName("OWNER signature");
	place.setAddress(address);

	ResponseCreatePlace responseCreatePlace = given()
		.log().all()
		.header("Authorization", "Bearer " + token)
		.contentType(ContentType.JSON)
		.body(place)
		.when()
		.post(dotenv.get("PLACE_CREATE"))
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.extract().response().as(ResponseCreatePlace.class);
	return new PlaceDTO(place);

}

}
