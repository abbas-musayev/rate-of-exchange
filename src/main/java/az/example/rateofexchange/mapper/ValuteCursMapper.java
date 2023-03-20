package az.example.rateofexchange.mapper;

import az.example.rateofexchange.domain.ValuteCursEnt;
import az.example.rateofexchange.domain.ValuteTypeEnt;
import az.example.rateofexchange.rest.dtos.response.ValuteCurs;
import az.example.rateofexchange.rest.dtos.response.ValuteType;
import az.example.rateofexchange.mapper.ValuteMapperImpl;
import az.example.rateofexchange.mapper.ValuteTypeMapperImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ValuteCursMapper {

    @Mapping(source = "valuteTypes", target = "valuteType", qualifiedByName = "mapValuteTypeToDto")
    @Mapping(source = "date", target = "date", qualifiedByName = "convertDateToString")
    ValuteCurs toDto(ValuteCursEnt entity);

    @Named("mapValuteTypeToDto")
    default Set<ValuteType> mapValuteTypeToDto(Set<ValuteTypeEnt> set) {
        ValuteTypeMapperImpl valuteTypeMapper = new ValuteTypeMapperImpl();
        ValuteMapperImpl valuteMapper = new ValuteMapperImpl();
        return set.stream()
                .map(b -> {
                    ValuteType bDto = valuteTypeMapper.toDto(b);
                    bDto.setValute(valuteMapper.toDto(b.getValutes()));
                    return bDto;
                })
                .collect(Collectors.toSet());
    }

    @Mapping(source = "valuteType", target = "valuteTypes", qualifiedByName = "mapValuteTypeToDEntity")
    @Mapping(source = "date", target = "date", qualifiedByName = "convertStringToDate")
    ValuteCursEnt toEntity(ValuteCurs dto);

    @Named("mapValuteTypeToDEntity")
    default Set<ValuteTypeEnt> mapValuteTypeToDEntity(Set<ValuteType> set) {
        ValuteTypeMapperImpl valuteTypeMapper = new ValuteTypeMapperImpl();
        ValuteMapperImpl valuteMapper = new ValuteMapperImpl();
        return set.stream()
                .map(b -> {
                    ValuteTypeEnt valuteTypeEnt = valuteTypeMapper.toEntity(b);
                    valuteTypeEnt.setValutes(valuteMapper.toEntity(b.getValute()));
                    return valuteTypeEnt;
                })
                .collect(Collectors.toSet());
    }

    @Named("convertStringToDate")
    static LocalDate convertStringToDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(str, formatter);
    }

    @Named("convertDateToString")
    static String convertDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(date);
    }
}
