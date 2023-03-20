package az.example.rateofexchange.mapper;

import az.example.rateofexchange.domain.ValuteEnt;
import az.example.rateofexchange.domain.ValuteTypeEnt;
//import az.example.rateofexchange.mapper.ValuteMapperImpl;
import az.example.rateofexchange.rest.dtos.response.Valute;
import az.example.rateofexchange.rest.dtos.response.ValuteType;
import az.example.rateofexchange.mapper.ValuteMapperImpl;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ValuteTypeMapper {

//    @Mapping(source = "valutes", target = "valute")
    @Mapping(source = "valutes",target = "valute")
    ValuteType toDto(ValuteTypeEnt entity);

    @Mapping(source = "valute", target = "valutes")
    ValuteTypeEnt toEntity(ValuteType dto);

//    @Named("mapSetC")
//    default Set<Valute> mapSetC(Set<ValuteEnt> set) {
//        ValuteMapperImpl valuteMapper = new ValuteMapperImpl();
//        return set.stream()
//                .map(valuteMapper::toDto)
//                .collect(Collectors.toSet());
//    }

    Set<ValuteType> toDto(Set<ValuteTypeEnt> entity);

    Set<ValuteTypeEnt> toEntity(Set<ValuteType> dto);
}
