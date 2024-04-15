package de.ait.users.service.impl;

import de.ait.users.dto.UserResponseDTO;
import de.ait.users.model.User;
import de.ait.users.repository.UserRepository;
import de.ait.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;
    private final Scanner scanner;


    @Override
    public List<UserResponseDTO> findAll() {

        return UserResponseDTO.from(repository.findAll());
    }

    @Override
    public List<UserResponseDTO> findAll(int age) {
        if (age == 0) {
            return findAll();
        } else {
            return UserResponseDTO.from(repository.findAll()).stream().filter(u -> u.getAge() == age).collect(Collectors.toList());
        }

    }

    @Override
    public Optional<User> findById(Long id) {

        return repository.findById(id);
    }

    @Override
    public User add(User user) {

        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        // TODO check user exit
        repository.deleteById(id);
    }
}
