package hookahapi.model.responce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AuthTokenResModel {


@JsonProperty(value = "type")
private String type;

@JsonProperty(value = "accessToken")
private String accessToken;

@JsonProperty(value = "refreshToken")
private String refreshToken;

}