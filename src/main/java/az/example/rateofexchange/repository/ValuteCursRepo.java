package az.example.rateofexchange.repository;

import az.example.rateofexchange.domain.ValuteCursEnt;
import az.example.rateofexchange.domain.ValuteEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ValuteCursRepo extends JpaRepository<ValuteCursEnt, Long> {

    @Query("select curs from ValuteCursEnt curs " +
            " left join fetch curs.valuteTypes types " +
            " left join fetch types.valutes val " +
            " where curs.date= :date" +
            " and curs.isDeleted= false " +
            " and types.isDeleted= false" +
            " and val.isDeleted= false"
    )
    ValuteCursEnt findByDate(@Param("date") LocalDate date);
}
