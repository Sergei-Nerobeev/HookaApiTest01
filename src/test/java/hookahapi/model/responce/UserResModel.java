package hookahapi.model.responce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class UserResModel {

@JsonKey
@JsonProperty(value = "id", required = true)
@NonNull
protected Long id;

@JsonProperty(value = "name")
private String name;

@JsonProperty(value = "email")
private String email;

@JsonProperty(value = "phone")
private String phone;

@JsonProperty(value = "is_enabled")
private boolean isEnabled;

@JsonProperty(value = "fcm_token")
private String fcmToken;

@JsonProperty(value = "roles", access = JsonProperty.Access.READ_ONLY)
private Set<RoleResModel> rolesSet;
}
