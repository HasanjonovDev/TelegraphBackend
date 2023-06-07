package uz.pdp.telegraphbackend.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostSearchDto {
    private String link;
    private UUID ownerId;
    private int page;
    private int size;
}
