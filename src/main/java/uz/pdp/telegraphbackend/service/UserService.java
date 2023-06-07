package uz.pdp.telegraphbackend.service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.telegraphbackend.dto.LoginDto;
import uz.pdp.telegraphbackend.dto.UserCreateDto;
import uz.pdp.telegraphbackend.dto.response.JwtResponse;
import uz.pdp.telegraphbackend.entity.UserEntity;
import uz.pdp.telegraphbackend.entity.UserRole;
import uz.pdp.telegraphbackend.exceptions.AuthenticationFailedException;
import uz.pdp.telegraphbackend.repository.UserRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService  {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserEntity add(UserCreateDto userCreateDto, List<UserRole> roles){
        UserEntity user = modelMapper.map(userCreateDto, UserEntity.class);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public JwtResponse signIn(LoginDto loginDto){
        UserEntity user = userRepository.findUserEntityByUsername(loginDto.getUsername())
                .orElseThrow(() -> new AuthenticationFailedException("username not found"));

        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            String token = jwtService.generateAccessToken(user);
            return JwtResponse.builder().token(token).build();
        }
        throw new AuthenticationFailedException("incorrect password");
    }
}
