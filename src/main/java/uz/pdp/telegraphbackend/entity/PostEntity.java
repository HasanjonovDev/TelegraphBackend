package uz.pdp.telegraphbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Entity(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostEntity extends BaseEntity {
    private String title;
    private String author;
    private String content;
    private String link;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity owner;
}
