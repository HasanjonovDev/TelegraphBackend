package uz.pdp.telegraphbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.telegraphbackend.dto.UserCreateDto;
import uz.pdp.telegraphbackend.entity.UserEntity;
import uz.pdp.telegraphbackend.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<UserEntity> addUser(
            @RequestBody UserCreateDto userCreateDto
            ){
        if (userService.searchByUsername(userCreateDto) == null){
            return ResponseEntity.ok(userService.add(userCreateDto));
        }
        return null;
    }
}
