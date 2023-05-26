package uz.pdp.telegraphbackend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.telegraphbackend.dto.PostCreateDto;
import uz.pdp.telegraphbackend.entity.PostEntity;
import uz.pdp.telegraphbackend.entity.UserEntity;
import uz.pdp.telegraphbackend.repository.PostRepository;
import uz.pdp.telegraphbackend.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostEntity add(PostCreateDto postCreateDto, UUID userId){
        PostEntity postEntity = modelMapper.map(postCreateDto, PostEntity.class);
        Optional<UserEntity> user = userRepository.findById(userId);
        UserEntity userEntity = user.orElse(null);
        if (userEntity!=null){
            String name = userEntity.getName().replaceAll(" ","");
            String link = name+"-"+ LocalDateTime.now();
            postEntity.setOwner(userEntity);
            postEntity.setLink(link);
            return postRepository.save(postEntity);
        }else {
            return null;
        }
    }
}
