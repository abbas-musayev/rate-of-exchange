package az.example.rateofexchange.rest.dtos.request;

import lombok.*;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserRequestDto {

    private String username;
    private String password;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "DUZGUN MAIL QEYD OLUNMAYIB")
    private String email;
}
