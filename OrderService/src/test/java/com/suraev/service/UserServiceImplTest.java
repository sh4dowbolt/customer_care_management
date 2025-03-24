package com.suraev.service;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;
import com.suraev.entity.enums.UserType;
import com.suraev.repository.UserEntityRepository;
import com.suraev.util.UserEntityMapper;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserEntityRepository userEntityRepository;
    @InjectMocks
    UserServiceImpl userService;

 @Nested
 class  getAllUsers {

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

 @Nested
    class createUser {

     @Test
     public void createUserInDb() {

         //given
         UserDTO userDTO = new UserDTO(1, "Michael", UserType.CASUAL);
         User user = UserEntityMapper.INSTANCE.toUser(userDTO);
         //when
         Mockito.when(userEntityRepository.save(user)).thenReturn(user);
         //then
         UserDTO result = userService.createUser(userDTO);

         assertThat(result.name()).isEqualTo(user.getName());

         }
     }

 @Nested
    class findByIdUser {

     @Test
     public void findUserById() {
         //given
         Integer id = 1;
         User user = new User(id,"Vitaly",UserType.CASUAL);
         //when
         Mockito.when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));
         //then
         Optional<UserDTO> actualResult = userService.getUserById(id);

         assertThat(actualResult).isPresent().get().extracting("id").isEqualTo(id);

     }

 }
 }





