package com.suraev.service;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAllUsers();

    User createUser(UserDTO userDTO);

    Optional<UserDTO> getUserById(Long id);

}
