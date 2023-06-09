package uz.pdp.telegraphbackend.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostCreateDto {
    @NotBlank(message = "Owner Id required")
    private UUID ownerId;
    @NotBlank(message = "Title cannot be blank or empty")
    private String title;
    @NotBlank(message = "Author cannot be blank or empty")
    private String author;
    @NotBlank(message = "Content cannot be blank or empty")
    private String content;
}
