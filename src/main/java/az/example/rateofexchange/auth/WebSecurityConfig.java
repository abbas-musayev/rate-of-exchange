package az.example.rateofexchange.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

import static org.springframework.http.HttpMethod.*;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SWAGGER2 = "/v2/api-docs";
    private static final String SWAGGER3 = "/v3/api-docs";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String SWAGGER_HTML = "/swagger-ui.html";

    private final JwtAuthSecurityConfigurerAdapter jwtAuthSecurityConfigurerAdapter;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(SWAGGER2).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER3).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER_UI).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER_HTML).permitAll();
        http.authorizeRequests()
                .antMatchers(POST, "/v1/currency").hasAuthority("ADMIN")
                .antMatchers(DELETE, "/v1/currency").hasAuthority("ADMIN")
                .antMatchers(GET, "/v1/currency/by-date").hasAuthority("USER")
                .antMatchers(GET, "/v1/currency/by-date-and-valute").hasAuthority("USER")
                .antMatchers(POST,"/v1/register").permitAll()
                .antMatchers(GET,"/v1/login").permitAll();
        http.logout().disable();
        http.formLogin().disable();
        http.apply(jwtAuthSecurityConfigurerAdapter);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        super.configure(http);
    }
}
