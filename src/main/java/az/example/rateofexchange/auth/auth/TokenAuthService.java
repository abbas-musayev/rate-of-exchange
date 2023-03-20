package az.example.rateofexchange.auth.auth;

import az.example.rateofexchange.auth.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenAuthService implements AuthService {

    public static final String BEARER = "Bearer";

    private final JwtService jwtService;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        log.info("TOKEN {}", authorization);
        String token = "";

        if (authorization != null && authorization.startsWith(BEARER)) {
            token = authorization.substring(BEARER.length());
            Claims claims = jwtService.parseToken(token);
            System.out.println("Claims is " + claims);
            Authentication authenticationBearer = jwtService.getAuthenticationBearerByClaims(claims);
            return Optional.of(authenticationBearer);
        } else {
            return Optional.empty();
        }
    }
}
