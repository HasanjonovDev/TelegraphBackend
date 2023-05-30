package uz.pdp.telegraphbackend.service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.telegraphbackend.dto.UserCreateDto;
import uz.pdp.telegraphbackend.entity.UserEntity;
import uz.pdp.telegraphbackend.exceptions.AuthenticationFailedException;
import uz.pdp.telegraphbackend.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserEntity add(UserCreateDto userCreateDto){
        UserEntity user = modelMapper.map(userCreateDto, UserEntity.class);
        return userRepository.save(user);
    }

    public UserEntity signIn(String username,String password){
        UserEntity user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new AuthenticationFailedException("username not found"));
        if (user.getPassword().equals(password)){
            return user;
        }
        throw new AuthenticationFailedException("incorrect password");
    }
}
