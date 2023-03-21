package az.example.rateofexchange.mapper;

import az.example.rateofexchange.domain.ValuteCursEnt;
import az.example.rateofexchange.rest.dtos.response.ValuteCurs;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-21T14:59:22+0400",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class ValuteCursMapperImpl implements ValuteCursMapper {

    @Override
    public ValuteCurs toDto(ValuteCursEnt entity) {
        if ( entity == null ) {
            return null;
        }

        ValuteCurs valuteCurs = new ValuteCurs();

        valuteCurs.setValuteType( mapValuteTypeToDto( entity.getValuteTypes() ) );
        valuteCurs.setDate( ValuteCursMapper.convertDateToString( entity.getDate() ) );
        valuteCurs.setName( entity.getName() );
        valuteCurs.setDescription( entity.getDescription() );

        return valuteCurs;
    }

    @Override
    public ValuteCursEnt toEntity(ValuteCurs dto) {
        if ( dto == null ) {
            return null;
        }

        ValuteCursEnt valuteCursEnt = new ValuteCursEnt();

        valuteCursEnt.setValuteTypes( mapValuteTypeToDEntity( dto.getValuteType() ) );
        valuteCursEnt.setDate( ValuteCursMapper.convertStringToDate( dto.getDate() ) );
        valuteCursEnt.setName( dto.getName() );
        valuteCursEnt.setDescription( dto.getDescription() );

        return valuteCursEnt;
    }
}
