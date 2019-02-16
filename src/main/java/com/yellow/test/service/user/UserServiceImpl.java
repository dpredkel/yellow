package com.yellow.test.service.user;

import com.yellow.test.entity.User;
import com.yellow.test.exception.Code;
import com.yellow.test.exception.ServiceRuntimeException;
import com.yellow.test.mapper.Mapper;
import com.yellow.test.model.user.CreateUserDTO;
import com.yellow.test.model.user.UserDTO;
import com.yellow.test.repository.UserRepository;
import com.yellow.test.service.msg.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MsgService msgService;

    @Autowired
    private Mapper<CreateUserDTO, User> mapper;

    @Autowired
    private Mapper<User, UserDTO> toDTOMapper;

    @Override
    public UserDTO save(CreateUserDTO dto) {
        User existing = repository.findByUsername(dto.getUsername());
        if (existing != null)
            throw new ServiceRuntimeException(msgService.msg(Code.C_1002.getMsg(), dto.getUsername()), Code.C_1002.getValue());

        User user = mapper.map(dto);
        User saved = repository.save(user);

        log.info("Created new user | username: {} , uuid: {}", saved.getUsername(), user.getUuid());
        return toDTOMapper.map(saved);
    }

}
