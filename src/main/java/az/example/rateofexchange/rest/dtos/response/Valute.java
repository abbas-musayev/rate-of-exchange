package az.example.rateofexchange.rest.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Valute {
    @JacksonXmlProperty(isAttribute = true, localName = "Nominal")
    private String nominal;
    @JacksonXmlProperty(isAttribute = true, localName = "Name")
    private String name;
    @JacksonXmlProperty(isAttribute = true, localName = "Value")
    private BigDecimal value;
}
