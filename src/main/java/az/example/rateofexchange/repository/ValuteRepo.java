package az.example.rateofexchange.repository;

import az.example.rateofexchange.domain.ValuteEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ValuteRepo extends JpaRepository<ValuteEnt,Long> {

    @Query("select val from ValuteCursEnt curs " +
            " join curs.valuteTypes types" +
            " join types.valutes val " +
            " where curs.date = ?1" +
            " and val.code= ?2" +
            " and curs.isDeleted = false" +
            " and types.isDeleted =  false" +
            " and val.isDeleted = false")
    ValuteEnt findByDateAndValute(LocalDate date, String code);
}
