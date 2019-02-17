package com.yellow.test.service.user;

import com.yellow.test.constant.NumberConstant;
import com.yellow.test.entity.User;
import com.yellow.test.exception.ServiceRuntimeException;
import com.yellow.test.mapper.Mapper;
import com.yellow.test.model.user.SaveUserDTO;
import com.yellow.test.model.user.UserDTO;
import com.yellow.test.repository.UserRepository;
import com.yellow.test.service.msg.MsgService;
import com.yellow.test.util.Generator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private MsgService msgService;

    @Mock
    private Mapper<SaveUserDTO, User> mapper;

    @Mock
    private Mapper<User, UserDTO> toDTOMapper;

    @Test(expected = ServiceRuntimeException.class)
    public void saveUserAlreadyExists() {
        SaveUserDTO dto = prepareDto();
        when(repository.findByUsername(dto.getUsername())).thenReturn(new User());
        when(msgService.msg(anyString(), anyCollection())).thenReturn(Generator.randomString());

        service.save(dto);
    }

    @Test
    public void saveUserNotExists() {
        SaveUserDTO dto = prepareDto();
        User mapped = User.builder().build();
        User saved = User.builder().build();
        UserDTO expectedResult = UserDTO.builder().build();

        when(repository.findByUsername(dto.getUsername())).thenReturn(null);
        when(mapper.map(dto)).thenReturn(mapped);
        when(repository.save(mapped)).thenReturn(saved);
        when(toDTOMapper.map(saved)).thenReturn(expectedResult);

        UserDTO actualResult = service.save(dto);

        verify(repository).findByUsername(dto.getUsername());
        verify(msgService, times(NumberConstant.ZERO)).msg(anyString(), anyCollection());

        verify(mapper).map(dto);
        verify(repository).save(mapped);
        verify(toDTOMapper).map(saved);
        Assert.assertEquals(expectedResult, actualResult);
    }

    private SaveUserDTO prepareDto() {
        return SaveUserDTO.builder()
                .username(Generator.randomString())
                .password(Generator.randomString())
                .build();
    }

}
