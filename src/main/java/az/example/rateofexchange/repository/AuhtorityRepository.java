package az.example.rateofexchange.repository;

import az.example.rateofexchange.domain.Authority;
import az.example.rateofexchange.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuhtorityRepository extends JpaRepository<Authority, Long> {

    Optional<Authority> findByRole(Role role);
}
