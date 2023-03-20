package az.example.rateofexchange.mapper;

import az.example.rateofexchange.domain.ValuteEnt;
import az.example.rateofexchange.domain.ValuteTypeEnt;
import az.example.rateofexchange.rest.dtos.response.Valute;
import az.example.rateofexchange.rest.dtos.response.ValuteType;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-20T19:03:37+0400",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class ValuteTypeMapperImpl implements ValuteTypeMapper {

    @Override
    public ValuteType toDto(ValuteTypeEnt entity) {
        if ( entity == null ) {
            return null;
        }

        ValuteType valuteType = new ValuteType();

        valuteType.setValute( valuteEntSetToValuteSet( entity.getValutes() ) );
        valuteType.setType( entity.getType() );

        return valuteType;
    }

    @Override
    public ValuteTypeEnt toEntity(ValuteType dto) {
        if ( dto == null ) {
            return null;
        }

        ValuteTypeEnt valuteTypeEnt = new ValuteTypeEnt();

        valuteTypeEnt.setValutes( valuteSetToValuteEntSet( dto.getValute() ) );
        valuteTypeEnt.setType( dto.getType() );

        return valuteTypeEnt;
    }

    @Override
    public Set<ValuteType> toDto(Set<ValuteTypeEnt> entity) {
        if ( entity == null ) {
            return null;
        }

        Set<ValuteType> set = new LinkedHashSet<ValuteType>( Math.max( (int) ( entity.size() / .75f ) + 1, 16 ) );
        for ( ValuteTypeEnt valuteTypeEnt : entity ) {
            set.add( toDto( valuteTypeEnt ) );
        }

        return set;
    }

    @Override
    public Set<ValuteTypeEnt> toEntity(Set<ValuteType> dto) {
        if ( dto == null ) {
            return null;
        }

        Set<ValuteTypeEnt> set = new LinkedHashSet<ValuteTypeEnt>( Math.max( (int) ( dto.size() / .75f ) + 1, 16 ) );
        for ( ValuteType valuteType : dto ) {
            set.add( toEntity( valuteType ) );
        }

        return set;
    }

    protected Valute valuteEntToValute(ValuteEnt valuteEnt) {
        if ( valuteEnt == null ) {
            return null;
        }

        Valute valute = new Valute();

        valute.setCode( valuteEnt.getCode() );
        valute.setNominal( valuteEnt.getNominal() );
        valute.setName( valuteEnt.getName() );
        valute.setValue( valuteEnt.getValue() );

        return valute;
    }

    protected Set<Valute> valuteEntSetToValuteSet(Set<ValuteEnt> set) {
        if ( set == null ) {
            return null;
        }

        Set<Valute> set1 = new LinkedHashSet<Valute>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ValuteEnt valuteEnt : set ) {
            set1.add( valuteEntToValute( valuteEnt ) );
        }

        return set1;
    }

    protected ValuteEnt valuteToValuteEnt(Valute valute) {
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

    protected Set<ValuteEnt> valuteSetToValuteEntSet(Set<Valute> set) {
        if ( set == null ) {
            return null;
        }

        Set<ValuteEnt> set1 = new LinkedHashSet<ValuteEnt>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Valute valute : set ) {
            set1.add( valuteToValuteEnt( valute ) );
        }

        return set1;
    }
}
