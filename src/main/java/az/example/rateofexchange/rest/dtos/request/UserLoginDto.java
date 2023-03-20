package az.example.rateofexchange.rest.dtos.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserLoginDto {
    private String username;
    private String password;
}
