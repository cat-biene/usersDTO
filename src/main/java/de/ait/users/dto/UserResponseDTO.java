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
@Schema(name = "UserClass", description = "System user")
public class UserResponseDTO {

    @Schema(description = "user id", example = "234")
    private Long id;
    @Schema(description = "username", example = "eni")
    private String name;
    @Schema(description = "user email", example = "email@email.de")
    private String email;
    @Schema(description = "user age", example = "23")
    private int age;

    public static UserResponseDTO from(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .age(user.getAge())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static List<UserResponseDTO> from(List<User> users) {
        return users.stream()
                .map(UserResponseDTO::from)
                .collect(Collectors.toList());
    }

}
