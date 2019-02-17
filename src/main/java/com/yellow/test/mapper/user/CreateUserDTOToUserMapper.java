package com.yellow.test.mapper.user;

import com.yellow.test.entity.Authority;
import com.yellow.test.entity.User;
import com.yellow.test.mapper.Mapper;
import com.yellow.test.model.user.SaveUserDTO;
import com.yellow.test.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUserDTOToUserMapper implements Mapper<SaveUserDTO, User> {

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User map(SaveUserDTO value) {
        if (value == null)
            return null;

        return User.builder()
                .uuid(Generator.uuidAsString())
                .username(value.getUsername())
                .password(encoder.encode(value.getPassword()))
                .authority(Authority.USER)
                .active(true)
                .build();
    }
}
