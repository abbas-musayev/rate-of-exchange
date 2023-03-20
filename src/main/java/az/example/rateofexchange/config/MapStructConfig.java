package az.example.rateofexchange.config;

import az.example.rateofexchange.mapper.ValuteCursMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public ValuteCursMapper valuteCursMapper(){
        return Mappers.getMapper(ValuteCursMapper.class);
    }
}
