package com.suraev.service;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;
import com.suraev.entity.enums.UserType;
import com.suraev.repository.UserRepository;
import com.suraev.util.UserMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.atLeastOnce;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

 @Nested
 class  getAllUsers {

    @Test
     public void shouldReturnAllUsersDTO() {

         //given
         List<User> usersList = List.of(
                 new User(1, "Vitaly", UserType.INDIVIDUAL),
                 new User(2, "Dmitry", UserType.INDIVIDUAL));
         //when
         Mockito.when(userRepository.findAll()).thenReturn(usersList);
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
                () -> Mockito.verify(userRepository, Mockito.atLeast(1)).findAll());
     }
 }

 @Nested
    class createUser {

     @Test
     public void createUserInDb() {

         //given
         UserDTO userDTO = new UserDTO(1, "Michael", UserType.INDIVIDUAL);
         User user = UserMapper.INSTANCE.toUser(userDTO);
         //when
         Mockito.when(userRepository.save(user)).thenReturn(user);
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
         User user = new User(id,"Vitaly",UserType.INDIVIDUAL);
         //when
         Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
         //then
         Optional<UserDTO> actualResult = userService.getUserById(id);

         assertThat(actualResult).isPresent().get().extracting("id").isEqualTo(id);

     }
 }
 @Nested
    class deleteUser {

     @Test
     public void deleteUserByIdIfExisted() {
         //given
         Integer id=1;
         //when
         Mockito.when(userRepository.existsById(id)).thenReturn(true);
         userRepository.deleteById(id);
         //then
         boolean actualResult = userService.deleteUser(id);
         Mockito.verify(userRepository, atLeastOnce()).deleteById(id);
         assertThat(actualResult).isTrue();
     }
     @Test
     public void deleteByIdIfNotExisted() {
         //given
         Integer id=2;
         //when
         Mockito.when(userRepository.existsById(id)).thenReturn(false);
         userRepository.deleteById(id);
         //then
         boolean actualResult = userService.deleteUser(id);
         Mockito.verify(userRepository, atLeastOnce()).deleteById(id);
         assertThat(actualResult).isFalse();
     }
 }
 }





