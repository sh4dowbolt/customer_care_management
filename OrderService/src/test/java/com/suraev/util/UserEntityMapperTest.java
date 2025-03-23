package com.suraev.util;

import com.suraev.dto.UserDTO;
import com.suraev.entity.User;
import com.suraev.entity.enums.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import static org.assertj.core.api.Assertions.*;


class UserEntityMapperTest {

    @Test
    public void shouldMapUserToUserDTO() {
        //given
        User user = new User(1, "Michael", UserType.CASUAL);
        user.getName();
        //when
        UserDTO userDTO = UserEntityMapper.INSTANCE.toUserDTO(user);
        //then
        assertThat(userDTO.name()).isEqualTo("Michael");
        assertThat(userDTO.type()).isEqualTo(UserType.CASUAL);


    }

}