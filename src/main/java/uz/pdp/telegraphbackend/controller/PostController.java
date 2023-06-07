package uz.pdp.telegraphbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegraphbackend.dto.PostCreateDto;
import uz.pdp.telegraphbackend.dto.PostSearchDto;
import uz.pdp.telegraphbackend.entity.PostEntity;
import uz.pdp.telegraphbackend.exceptions.RequestValidationException;
import uz.pdp.telegraphbackend.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/add")
    public PostEntity add(
            @Valid @RequestBody PostCreateDto postCreateDto,
            BindingResult bindingResult
            ){
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw  new RequestValidationException(errors);
        }
        return postService.add(postCreateDto);
    }

    @PostMapping("/search")
    public Page<PostEntity> search(
            @RequestBody PostSearchDto postSearchDto
    ){
        return postService.searchByLink(postSearchDto.getLink(),postSearchDto.getPage(),postSearchDto.getSize());
    }

    @PostMapping("/myPosts")
    public Page<PostEntity> myPosts(
            @RequestBody PostSearchDto postSearchDto
    ){
        return  postService.getByOwnerId(postSearchDto.getOwnerId(), postSearchDto.getSize(), postSearchDto.getPage());
    }
}
