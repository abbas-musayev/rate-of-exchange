package az.example.rateofexchange.auth;

import az.example.rateofexchange.auth.auth.AuthService;
import az.example.rateofexchange.exception.AuthRequestExcetion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JwtAuthRequestFilter extends OncePerRequestFilter {

    public static final String BEARER = "Bearer";

    private final List<AuthService> authServices;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JWT AUTH REQUEST FILTER CALLED");

        try {
            Optional<Authentication> authOptional = Optional.empty();

            for (AuthService authService : authServices) {
                authOptional = authOptional.or(() -> authService.getAuthentication(request));
            }
            authOptional.ifPresent((auth) -> SecurityContextHolder.getContext().setAuthentication(auth));
            filterChain.doFilter(request, response);
        }catch (Exception ex){
            log.error("**** CATCH BASIC AUTH REQUEST EXCEPTION **** : {} ", ex.getMessage());
            throw  new AuthRequestExcetion(400,"AUTH-EXCEPTION","AUTHENTICATION ZAMANI XETA BAS VERDI");
//            response.setHeader("error",ex.getMessage());
//            response.setStatus(FORBIDDEN.value());
//            Map<String,String > error = new HashMap<>();
//            error.put("error_message",ex.getMessage());
//            response.setContentType(APPLICATION_JSON_VALUE);
//
////            ErrorResponseDto responseDetails = ErrorResponseDto.builder()
////                    .status(ex.getStatus())
////                    .code(ex.getCode())
////                    .path(path)
////                    .timeStamp(LocalDateTime.now())
////                    .message(translationRepoService.findByKey(message,lang))
////                    .detail(translationRepoService.findByKey(ex.getMessage()+"_DETAIL",lang))
////                    .build();
//
////            response.getWriter().write(new ObjectMapper().writeValueAsString(errorDetails));
//            new ObjectMapper().writeValue(response.getOutputStream(),error);
////            throw new CredentialsExpiredException("Expired jwt credentials ", ex);
        }
    }
}