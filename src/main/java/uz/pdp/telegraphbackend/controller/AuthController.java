package uz.pdp.telegraphbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegraphbackend.dto.LoginDto;
import uz.pdp.telegraphbackend.dto.UserCreateDto;
import uz.pdp.telegraphbackend.dto.response.JwtResponse;
import uz.pdp.telegraphbackend.entity.UserEntity;
import uz.pdp.telegraphbackend.entity.UserRole;
import uz.pdp.telegraphbackend.exceptions.RequestValidationException;
import uz.pdp.telegraphbackend.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/add")
    public UserEntity addUser(
            @Valid @RequestBody UserCreateDto userCreateDto,
            BindingResult bindingResult
            ){
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new RequestValidationException(errors);
        }
        return userService.add(userCreateDto,List.of(UserRole.ROLE_USER));
    }

    @GetMapping ("/login")
    public ResponseEntity<JwtResponse> signIn(
            @RequestBody LoginDto loginDto
    ){
        return ResponseEntity.ok(userService.signIn(loginDto));
    }
}
