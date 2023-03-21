package az.example.rateofexchange;

import az.example.rateofexchange.auth.JwtService;
import az.example.rateofexchange.domain.Authority;
import az.example.rateofexchange.domain.User;
import az.example.rateofexchange.enums.Role;
import az.example.rateofexchange.repository.AuhtorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Set;

import static az.example.rateofexchange.enums.Role.ADMIN;
import static az.example.rateofexchange.enums.Role.USER;

@SpringBootApplication
@EntityScan("az.example.rateofexchange.domain")
@RequiredArgsConstructor
@EnableSwagger2
@Slf4j
public class RateofExchangeApplication implements CommandLineRunner {

	private final AuhtorityRepository auhtorityRepository;
	private final JwtService jwtService;

	public static void main(String[] args) {
		SpringApplication.run(RateofExchangeApplication.class, args);
	}

	@Override
	public void run(String... args){
		auhtorityRepository.saveAll(Set.of(new Authority(null,USER),new Authority(null,ADMIN)));

		Authority a = new Authority();
        a.setRole(ADMIN);
        User user = User.builder()
				.id(1L)
                .username("Abbas")
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .authorities(Set.of(a))
                .build();
		String s = jwtService.issueToken(user);
		log.info("ADMIN TOKEN {}",s);
	}
}
