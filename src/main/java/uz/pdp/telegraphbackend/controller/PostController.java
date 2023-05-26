package uz.pdp.telegraphbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.telegraphbackend.dto.PostCreateDto;
import uz.pdp.telegraphbackend.entity.PostEntity;
import uz.pdp.telegraphbackend.service.PostService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/add")
    public ResponseEntity<PostEntity> add(
            @RequestBody PostCreateDto postCreateDto,
            @RequestParam UUID ownerId
            ){
        PostEntity post = postService.add(postCreateDto, ownerId);
        if (post!=null){
             return ResponseEntity.ok(post);
        }
        return null;
    }
}
