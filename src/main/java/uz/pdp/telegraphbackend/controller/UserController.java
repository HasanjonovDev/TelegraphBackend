package uz.pdp.telegraphbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegraphbackend.dto.UserCreateDto;
import uz.pdp.telegraphbackend.entity.UserEntity;
import uz.pdp.telegraphbackend.exceptions.RequestValidationException;
import uz.pdp.telegraphbackend.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
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
        return userService.add(userCreateDto);
    }

    @PostMapping("/sign-in")
    public UserEntity signIn(
            @RequestBody UserCreateDto userCreateDto
    ){
        return userService.signIn(userCreateDto);
    }
}
