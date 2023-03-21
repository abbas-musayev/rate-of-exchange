package az.example.rateofexchange.mapper;

import az.example.rateofexchange.domain.ValuteEnt;
import az.example.rateofexchange.rest.dtos.response.Valute;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-21T17:37:14+0400",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class ValuteMapperImpl implements ValuteMapper {

    @Override
    public Valute toDto(ValuteEnt entity) {
        if ( entity == null ) {
            return null;
        }

        Valute valute = new Valute();

        valute.setCode( entity.getCode() );
        valute.setNominal( entity.getNominal() );
        valute.setName( entity.getName() );
        valute.setValue( entity.getValue() );

        return valute;
    }

    @Override
    public ValuteEnt toEntity(Valute valute) {
        if ( valute == null ) {
            return null;
        }

        ValuteEnt valuteEnt = new ValuteEnt();

        valuteEnt.setCode( valute.getCode() );
        valuteEnt.setNominal( valute.getNominal() );
        valuteEnt.setName( valute.getName() );
        valuteEnt.setValue( valute.getValue() );

        return valuteEnt;
    }

    @Override
    public Set<Valute> toDto(Set<ValuteEnt> entity) {
        if ( entity == null ) {
            return null;
        }

        Set<Valute> set = new LinkedHashSet<Valute>( Math.max( (int) ( entity.size() / .75f ) + 1, 16 ) );
        for ( ValuteEnt valuteEnt : entity ) {
            set.add( toDto( valuteEnt ) );
        }

        return set;
    }

    @Override
    public Set<ValuteEnt> toEntity(Set<Valute> valute) {
        if ( valute == null ) {
            return null;
        }

        Set<ValuteEnt> set = new LinkedHashSet<ValuteEnt>( Math.max( (int) ( valute.size() / .75f ) + 1, 16 ) );
        for ( Valute valute1 : valute ) {
            set.add( toEntity( valute1 ) );
        }

        return set;
    }
}
