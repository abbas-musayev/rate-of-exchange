package az.example.rateofexchange.auth.auth;

import az.example.rateofexchange.auth.JwtService;
import az.example.rateofexchange.domain.User;
import az.example.rateofexchange.exception.NotFoundCustomException;
import az.example.rateofexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Optional;

import static az.example.rateofexchange.exception.ErrorCodesEnum.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasicAuthService implements AuthService {
    public static final String BASIC = "Basic ";

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        log.info("TOKEN {}", authorization);
        String basic = "";

        if (authorization != null && authorization.startsWith(BASIC)) {

            basic = authorization.substring(BASIC.length());

            byte[] decode = Base64.getDecoder()
                    .decode(basic);

            String[] credentials = new String(decode).split(":");

            if (credentials.length != 2) {
                throw new RuntimeException("");
            }
            String username = credentials[0];
            String password = credentials[1];
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new NotFoundCustomException(USER_NOT_FOUND,"User not found when login"));

            boolean matches = passwordEncoder.matches(password, user.getPassword());

            if (matches) {
                return Optional.of(jwtService.getAuthenticationBearerByUser(user));
            } else {
                throw new RuntimeException("USERNAME OR PASSWORD IS NOT CORRECT");
            }
        }else {
            return Optional.empty();
        }
    }
}
