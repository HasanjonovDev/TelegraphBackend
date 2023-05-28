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

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/post")
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

    @PostMapping("/search")
    public ResponseEntity<List<PostEntity>> search(
            @RequestParam String link
    ){
        List<PostEntity> posts = postService.searchByLink(link);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/myPosts")
    public ResponseEntity<List<PostEntity>> myPosts(
            @RequestParam UUID ownerId
    ){
        List<PostEntity> byOwnerId = postService.getByOwnerId(ownerId);
        return ResponseEntity.ok(byOwnerId);
    }
}
