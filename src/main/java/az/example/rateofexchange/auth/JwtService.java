package az.example.rateofexchange.auth;

import az.example.rateofexchange.domain.Authority;
import az.example.rateofexchange.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtService {

    private Key key;

    @Value("${security.secret.key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String issueToken(User user) {
        log.info("Issue JWT token to {}", user);
        final JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(new Date())
                .claim("username", user.getUsername())
                .claim("userId", user.getId())
                .claim("role", user.getAuthorities().stream().map(authority -> authority.getRole().name()).collect(
                        Collectors.toList()))
                .setExpiration(Date.from(Instant.now().plusSeconds(1000_000)))
                .setHeader(Map.of("type", "JWT"))
                .signWith(key, SignatureAlgorithm.HS256);
        return jwtBuilder.compact();
    }
    
    public Authentication getAuthenticationBearerByClaims(Claims claims){
        List<?> roles = claims.get("role", List.class);

        List<SimpleGrantedAuthority> authorityList = roles.stream()
                .map(a -> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims,"",authorityList);
    }


    public Authentication getAuthenticationBearerByUser(User user){
        List<?> roles = user.getAuthorities().stream()
                .map(Authority::getRole)
                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorityList = roles.stream()
                .map(a -> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(user,"",authorityList);
    }

}
