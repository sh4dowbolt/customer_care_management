package com.suraev.util;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;
import com.suraev.entity.enums.UserType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserMapperTest {

    @Test
    public void shouldMapUserToUserDTO() {
        //given
        User user = new User(1, "Michael", UserType.INDIVIDUAL);
        //when
        UserDTO userDTO = UserMapper.INSTANCE.toUserDTO(user);
        //then
        assertThat(userDTO.name()).isEqualTo("Michael");
        assertThat(userDTO.type()).isEqualTo(UserType.INDIVIDUAL);

    }

    @Test
    public void shouldMapUserDTOtoUser() {
        //given
        UserDTO userDTO = new UserDTO(1,"Josh",UserType.INDIVIDUAL);
        //when
        User user = UserMapper.INSTANCE.toUser(userDTO);
        //then
        assertThat(user.getName()).isEqualTo("Josh");
        assertThat(user.getType()).isEqualTo(UserType.INDIVIDUAL);
    }

}