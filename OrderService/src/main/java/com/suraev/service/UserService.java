package com.suraev.service;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    User createUser(UserDTO userDTO);
}
