package de.ait.users.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Schema(name = "UserClass", description = "System user")

public class User {
    @Schema(description = "user id", example = "234")
    @Setter
    private Long id;
    @Schema(description = "", example = "eni")
    private String name;
    @Schema(description = "user password", example = "enj!24")
    private String password;
    @Schema(description = "user email", example = "email@email.de")
    private String email;
    @Schema(description = "user age", example = "23")
    private int age;

}
