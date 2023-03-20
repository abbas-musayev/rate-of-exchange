package az.example.rateofexchange.services.impl;

import az.example.rateofexchange.auth.JwtService;
import az.example.rateofexchange.domain.Authority;
import az.example.rateofexchange.domain.User;
import az.example.rateofexchange.enums.Role;
import az.example.rateofexchange.exception.CustomThrowException;
import az.example.rateofexchange.exception.NotFoundCustomException;
import az.example.rateofexchange.repository.AuhtorityRepository;
import az.example.rateofexchange.repository.UserRepository;
import az.example.rateofexchange.rest.dtos.request.UserLoginDto;
import az.example.rateofexchange.rest.dtos.request.UserRequestDto;
import az.example.rateofexchange.rest.dtos.response.UserResponseDto;
import az.example.rateofexchange.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static az.example.rateofexchange.exception.ErrorCodesEnum.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuhtorityRepository auhtorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponseDto getUserByName(String name) {
        User user = userRepository
                .findByUsername(name)
                .orElseThrow(() -> new NotFoundCustomException(USER_NOT_FOUND,"User name "+name+" was not found"));

        return UserResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {

        Authority authority = auhtorityRepository
                .findByRole(Role.USER)
                .orElseThrow(() -> new NotFoundCustomException(ROLE_NOT_FOUND,"Role was not found"));

        User build = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .authorities(Set.of(authority))
                .isAccountNonExpired(true)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();
        userRepository.save(build);

        return UserResponseDto.builder()
                .responseMessage("User Created")
                .build();
    }

    @Override
    public String loginUser(UserLoginDto dto) {

        User user = userRepository
                .findByUsername(dto.getUsername())
                .orElseThrow(() -> new NotFoundCustomException(USER_NOT_FOUND,"User name "+dto.getUsername()+" was not found"));

        boolean matches = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if (matches){
            return jwtService.issueToken(user);
        }else {
            throw new CustomThrowException(401,PASSWORD_IS_NOT_CORRECT,"Password is not correct");
        }

    }
}
