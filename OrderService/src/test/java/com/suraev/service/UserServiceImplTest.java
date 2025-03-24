package com.suraev.service;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;
import com.suraev.entity.enums.UserType;
import com.suraev.repository.UserEntityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


 @Nested
 class  getAllUsers {

     @Mock
     UserEntityRepository userEntityRepository;
     @InjectMocks
     UserServiceImpl userService;

    @Test
     public void shouldReturnAllUsersDTO() {

         //given
         List<User> usersList = List.of(
                 new User(1, "Vitaly", UserType.CASUAL),
                 new User(2, "Dmitry", UserType.VIP));
         //when
         Mockito.when(userEntityRepository.findAll()).thenReturn(usersList);
         List<UserDTO> usersDTOlist = userService.getAllUsers();
         //then

        assertAll(
                () -> assertThat(usersDTOlist).hasSameSizeAs(usersList),
                () -> assertThat(usersDTOlist)
                .extracting("id", "name", "type")
                .containsExactlyInAnyOrderElementsOf(
                        usersList.stream()
                                .map(entity -> tuple(entity.getId(), entity.getName(), entity.getType()))
                                .toList()
                ),
                () -> Mockito.verify(userEntityRepository, Mockito.atLeast(1)).findAll());

     }

 }

}