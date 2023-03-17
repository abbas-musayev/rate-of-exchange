package az.example.rateofexchange.rest.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValuteCurs {

    @JacksonXmlProperty(isAttribute = true, localName = "Date")
    String date;
    @JacksonXmlProperty(isAttribute = true, localName = "Name")
    String name;
    @JacksonXmlProperty(isAttribute = true, localName = "Description")
    String description;
    @JacksonXmlProperty(localName = "ValType")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ValuteType> valuteType;
}
