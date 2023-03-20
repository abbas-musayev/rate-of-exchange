package az.example.rateofexchange.rest;

import az.example.rateofexchange.rest.dtos.request.UserLoginDto;
import az.example.rateofexchange.rest.dtos.request.UserRequestDto;
import az.example.rateofexchange.rest.dtos.response.UserResponseDto;
import az.example.rateofexchange.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto dto) {
        return ResponseEntity.ok(userService.loginUser(dto));
    }
}
