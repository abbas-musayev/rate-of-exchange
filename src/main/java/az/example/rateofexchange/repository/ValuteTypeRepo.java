package az.example.rateofexchange.repository;

import az.example.rateofexchange.domain.ValuteTypeEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuteTypeRepo extends JpaRepository<ValuteTypeEnt,Long> {
}
