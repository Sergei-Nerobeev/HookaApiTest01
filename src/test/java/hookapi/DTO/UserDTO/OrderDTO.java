package hookapi.DTO.UserDTO;

import hookapi.entity.order.response.ResponseCreateOrder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
private ResponseCreateOrder responseCreateOrder;
}
