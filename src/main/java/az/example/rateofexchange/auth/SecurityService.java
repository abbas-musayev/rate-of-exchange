package az.example.rateofexchange.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    public JwtCredentials getCurrentJwtCredentials() {
        var context = SecurityContextHolder.getContext();
        return new ObjectMapper().convertValue(context.getAuthentication().getPrincipal(), JwtCredentials.class);
    }
}
