package com.suraev.service;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;
import com.suraev.repository.UserEntityRepository;
import com.suraev.util.UserEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserEntityRepository userEntityRepository;

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> usersFromDB = userEntityRepository.findAll();

        return usersFromDB
                .stream().map(UserEntityMapper.INSTANCE::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return Optional.empty();
    }
}
