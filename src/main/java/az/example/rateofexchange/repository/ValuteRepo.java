package az.example.rateofexchange.repository;

import az.example.rateofexchange.domain.ValuteEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuteRepo extends JpaRepository<ValuteEnt,Long> {
}
