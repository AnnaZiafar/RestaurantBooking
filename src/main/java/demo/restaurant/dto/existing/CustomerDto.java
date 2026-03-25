package demo.restaurant.dto.existing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    Long id;
    String fullName;
    String email;
}
