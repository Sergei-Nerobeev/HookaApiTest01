package hookahapi.model.responce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class RoleResModel {

@JsonKey
@JsonProperty(value = "id", required = true)
@NonNull
protected Long id;

@JsonProperty("role_name")
private String roleName;
}
