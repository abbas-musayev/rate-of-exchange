package az.example.rateofexchange.rest.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private String username;
    private String password;
    private String email;
    private String responseMessage;
}
