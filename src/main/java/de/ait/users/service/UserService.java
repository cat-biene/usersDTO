package de.ait.users.service;

import de.ait.users.dto.UserRequestDTO;
import de.ait.users.dto.UserResponseDTO;
import de.ait.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> findAll();

    List<UserResponseDTO> findAll(int age);

    Optional<User> findById(Long id);

    User add(UserRequestDTO user);

    void deleteById(Long id);
}
