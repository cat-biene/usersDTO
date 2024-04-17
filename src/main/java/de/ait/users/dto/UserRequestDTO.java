package de.ait.users.dto;

import de.ait.users.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@ToString
@Builder
@Schema(name = "User request DTO", description = "DTO")

public class UserRequestDTO {

    @Schema(description = "", example = "eni")
    private String name;
    @Schema(description = "user password", example = "enj!24")
    private String password;
    @Schema(description = "user email", example = "email@email.de")
    private String email;
    @Schema(description = "user age", example = "23")
    private int age;

    public static UserRequestDTO from(User user) {
        return UserRequestDTO.builder()
                .age(user.getAge())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static List<UserRequestDTO> from(List<User> users) {
        return users.stream()
                .map(UserRequestDTO::from)
                .collect(Collectors.toList());
    }

}
