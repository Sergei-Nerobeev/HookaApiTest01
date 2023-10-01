package hookahapi.model.responce.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderResModel {

@JsonProperty(value = "id")
private Long id;

@JsonProperty(value = "takenAt")
private Instant takenAt;

@JsonProperty(value = "completedAt")
private Instant completedAt;

@JsonProperty(value = "cancelledAt")
private Instant cancelledAt;

@JsonProperty(value = "order_status")
private String order_status;

@JsonProperty(value = "created_at")
private String created_at;

@JsonProperty(value = "updated_at")
private String updated_at;

@JsonProperty(value = "deleted_at")
private String deleted_at;

@JsonKey
@JsonProperty(value = "place_id")
private PlaceId placeId;

@JsonProperty(value = "user_id")
private UserId user_id;

@JsonProperty(value = "order_time")
private String order_time;

@JsonProperty(value = "comment")
private CommentOrder comment;



//@JsonProperty(value = "phone")
//private String phone;
//
//@JsonProperty(value = "owner")
//private UserResModel owner;
//
//@JsonProperty(value = "address")
//private AddressResModel address;


}
