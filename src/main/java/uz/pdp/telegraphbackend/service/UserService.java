package uz.pdp.telegraphbackend.service;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.telegraphbackend.dto.UserCreateDto;
import uz.pdp.telegraphbackend.entity.UserEntity;
import uz.pdp.telegraphbackend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserEntity searchByUsername(UserCreateDto userCreateDto){
        try {
            UserEntity user = userRepository.findByUsername(userCreateDto.getUsername());
            if (user != null){
                return user;
            }
        }catch (NoResultException ignored){
        }
        return null;
    }

    public UserEntity add(UserCreateDto userCreateDto){
        UserEntity user = modelMapper.map(userCreateDto, UserEntity.class);
        return userRepository.save(user);
    }
}
