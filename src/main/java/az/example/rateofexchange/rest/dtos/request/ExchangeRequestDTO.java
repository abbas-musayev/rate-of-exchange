package az.example.rateofexchange.rest.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
public class ExchangeRequestDTO {
//    ^(0[1-9]|[1-2][0-9]|3[0-1])\.(0[1-9]|1[0-2])\.\d{4}$
    @Pattern(regexp = "^(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.\\d{4}$",message = "Xetaaaa")
//    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private String date;
}
