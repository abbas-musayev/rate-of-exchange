package az.example.rateofexchange.repository;

import az.example.rateofexchange.domain.ValuteTypeEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Set;

public interface ValuteTypeRepo extends JpaRepository<ValuteTypeEnt,Long> {

    @Query("select types from ValuteCursEnt curs" +
            " join curs.valuteTypes types " +
            " join fetch types.valutes val " +
            " where curs.date= ?1 "+
            " and curs.isDeleted = false " +
            " and types.isDeleted =  false " +
            " and val.isDeleted = false ")
    Set<ValuteTypeEnt> findByDate(LocalDate localDate);


    @Query("select types from ValuteCursEnt curs" +
            " join curs.valuteTypes types " +
            " join fetch types.valutes val " +
            " where val.code= :code "+
            " and curs.isDeleted = false " +
            " and types.isDeleted =  false " +
            " and val.isDeleted = false ")
    Set<ValuteTypeEnt> findByValute(@Param("code") String code);
}
