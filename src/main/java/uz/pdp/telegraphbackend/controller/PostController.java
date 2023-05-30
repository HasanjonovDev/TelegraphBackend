package uz.pdp.telegraphbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegraphbackend.dto.PostCreateDto;
import uz.pdp.telegraphbackend.entity.PostEntity;
import uz.pdp.telegraphbackend.exceptions.RequestValidationException;
import uz.pdp.telegraphbackend.service.PostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/add")
    public PostEntity add(
            @Valid @RequestBody PostCreateDto postCreateDto,
            @RequestParam UUID ownerId,
            BindingResult bindingResult
            ){
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw  new RequestValidationException(errors);
        }
        return postService.add(postCreateDto, ownerId);
    }

    @PostMapping("/search")
    public Page<PostEntity> search(
            @RequestParam String link,
            @RequestParam(required = false,defaultValue = "0") String page,
            @RequestParam(required = false,defaultValue = "10") String size
    ){
        return postService.searchByLink(link,Integer.parseInt(page),Integer.parseInt(size));
    }

    @PostMapping("/myPosts")
    public Page<PostEntity> myPosts(
            @RequestParam UUID ownerId,
            @RequestParam(required = false,defaultValue = "0") String page,
            @RequestParam(required = false,defaultValue = "10") String size
    ){
        return  postService.getByOwnerId(ownerId,Integer.parseInt(size),Integer.parseInt(page));
    }
}
