package az.example.rateofexchange.repository;

import az.example.rateofexchange.domain.ValuteCursEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuteCursRepo extends JpaRepository<ValuteCursEnt,Long> {
}
