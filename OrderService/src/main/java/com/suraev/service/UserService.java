package com.suraev.service;

import com.suraev.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO createUser(UserDTO userDTO);

    Optional<UserDTO> getUserById(Integer id);

    boolean deleteUser(Integer id);

}
