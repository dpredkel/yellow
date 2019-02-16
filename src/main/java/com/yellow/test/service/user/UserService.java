package com.yellow.test.service.user;

import com.yellow.test.model.user.CreateUserDTO;
import com.yellow.test.model.user.UserDTO;

public interface UserService {

    UserDTO save(CreateUserDTO dto);

}
