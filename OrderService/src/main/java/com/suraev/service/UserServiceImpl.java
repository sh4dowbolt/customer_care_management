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
    public UserDTO createUser(UserDTO userDTO) {

        final var user = UserEntityMapper.INSTANCE.toUser(userDTO);
        User userToDB = userEntityRepository.save(user);
        return UserEntityMapper.INSTANCE.toUserDTO(userToDB);
    }

    @Override
    public Optional<UserDTO> getUserById(Integer id) {
        return userEntityRepository.findById(id).map(UserEntityMapper.INSTANCE::toUserDTO);
    }

    @Override
    public boolean deleteUser(Integer id) {
        if(userEntityRepository.existsById(id)) {
            userEntityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
