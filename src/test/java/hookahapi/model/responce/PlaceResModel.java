package hookahapi.model.responce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class PlaceResModel {

@JsonKey
@JsonProperty(value = "id")
protected Long id;
@JsonProperty(value = "name")
private String name;
@JsonProperty(value = "start_time")
private String startTime;
@JsonProperty(value = "end_time")
private String endTime;
@JsonProperty(value = "logo_url")
private String logoUrl;
@JsonProperty(value = "phone")
private String phone;
@JsonProperty(value = "owner")
private UserResModel owner;
@JsonProperty(value = "address")
private AddressResModel address;
@JsonProperty(value = "staff")
private Set<UserResModel> staff;
}
