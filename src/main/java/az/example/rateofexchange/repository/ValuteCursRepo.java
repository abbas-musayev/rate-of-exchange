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

    @Query("select curs from ValuteCursEnt curs "
            +
            " left join fetch curs.valuteTypes types " +
            " left join fetch types.valutes " +
            " where curs.date= :date"
    )
    ValuteCursEnt findByDate(@Param("date") LocalDate date);
}
