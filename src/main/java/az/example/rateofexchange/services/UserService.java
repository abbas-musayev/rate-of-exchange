package az.example.rateofexchange.services;

import az.example.rateofexchange.rest.dtos.request.UserLoginDto;
import az.example.rateofexchange.rest.dtos.request.UserRequestDto;
import az.example.rateofexchange.rest.dtos.response.UserResponseDto;

public interface UserService {

    UserResponseDto getUserByName(String name);

    UserResponseDto createUser(UserRequestDto dto);

    String loginUser(UserLoginDto dto);

}
