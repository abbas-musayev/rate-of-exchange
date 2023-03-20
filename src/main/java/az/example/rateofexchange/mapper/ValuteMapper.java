package az.example.rateofexchange.mapper;

import az.example.rateofexchange.domain.ValuteEnt;
import az.example.rateofexchange.rest.dtos.response.Valute;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ValuteMapper {

    Valute toDto(ValuteEnt entity);
    ValuteEnt toEntity(Valute valute);

    Set<Valute> toDto(Set<ValuteEnt> entity);
    Set<ValuteEnt> toEntity(Set<Valute> valute);

}
