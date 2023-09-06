package hookapi.service;

import hookapi.DTO.PlaceDTO;
import hookapi.DTO.UserDTO;
import hookapi.entity.order.response.Address;
import hookapi.entity.place.RequestCreatePlace;
import hookapi.entity.place.ResponseCreatePlace;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class PlaceGenerator {

private ResponseCreatePlace place = new ResponseCreatePlace();

public PlaceDTO createPlace()
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

	place.setName("OWNER signature");
	place.setAddress(address);

	Dotenv dotenv = Dotenv.load();
	UserDTO newOwner = UserGenerator.getInstance().createOwner();
	var token = newOwner.getAuthToken();

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
