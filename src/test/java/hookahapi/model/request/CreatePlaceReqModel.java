package hookahapi.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hookahapi.model.responce.AddressResModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
@RequiredArgsConstructor

public class CreatePlaceReqModel {
@NonNull
@JsonProperty(value = "name", required = true)
private String placeName;

@NonNull
@JsonProperty(value = "address")
private AddressResModel address;
}
