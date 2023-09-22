package hookahapi.model.responce;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

public class AddressResModel {

@JsonKey
@JsonProperty(value = "id")
protected Long id;

@JsonProperty(value = "country")
private String country;

@JsonProperty(value = "updated_at")
private String updated;

@JsonProperty(value = "created_at")
private String created;

@JsonProperty(value = "deleted_at")
private String deleted;

@NonNull
@JsonProperty(value =  "address")
private String address;

@JsonProperty(value =  "lat")
private double lat;

@JsonProperty(value = "lng")
private double lng;
}
