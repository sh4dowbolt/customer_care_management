package com.suraev.service;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;
import com.suraev.repository.UserRepository;
import com.suraev.util.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> usersFromDB = userRepository.findAll();

        return usersFromDB
                .stream().map(UserMapper.INSTANCE::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        final var user = UserMapper.INSTANCE.toUser(userDTO);
        User userToDB = userRepository.save(user);
        return UserMapper.INSTANCE.toUserDTO(userToDB);
    }

    @Override
    public Optional<UserDTO> getUserById(Integer id) {
        return userRepository.findById(id).map(UserMapper.INSTANCE::toUserDTO);
    }

    @Override
    public boolean deleteUser(Integer id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
