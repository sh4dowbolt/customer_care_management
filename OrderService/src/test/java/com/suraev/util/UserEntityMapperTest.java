package com.suraev.util;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;
import com.suraev.entity.enums.UserType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserEntityMapperTest {

    @Test
    public void shouldMapUserToUserDTO() {
        //given
        User user = new User(1, "Michael", UserType.CASUAL);
        //when
        UserDTO userDTO = UserEntityMapper.INSTANCE.toUserDTO(user);
        //then
        assertThat(userDTO.name()).isEqualTo("Michael");
        assertThat(userDTO.type()).isEqualTo(UserType.CASUAL);

    }

    @Test
    public void shouldMapUserDTOtoUser() {
        //given
        UserDTO userDTO = new UserDTO("Josh",UserType.CASUAL);
        //when
        User user = UserEntityMapper.INSTANCE.toUser(userDTO);
        //then
        assertThat(user.getName()).isEqualTo("Josh");
        assertThat(user.getType()).isEqualTo(UserType.CASUAL);
    }

}