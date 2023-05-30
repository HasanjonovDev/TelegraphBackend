package uz.pdp.telegraphbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {
    private String name;
    @NotBlank(message = "Username cannot be blank or empty")
    private String username;
    @NotBlank(message = "Password cannot be blank or empty")
    private String password;
}
