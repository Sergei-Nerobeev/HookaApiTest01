package hookahapi.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class CreateUserReqModel {

@JsonProperty(value = "phone")
private String mobilPhone;

@JsonProperty(value = "password")
private String password;
}
