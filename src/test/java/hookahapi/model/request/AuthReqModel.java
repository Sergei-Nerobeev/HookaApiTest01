package hookahapi.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Setter
@Getter
@JsonSerialize

public class AuthReqModel {

@JsonProperty(value = "phone")
private String mobilePhone;

@JsonProperty(value = "password")
private String password;

}
