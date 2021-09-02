package com.mbenz.file.storageservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mbenz.file.storageservice.dto.FileType;
import com.mbenz.file.storageservice.dto.UserRequest;
import com.mbenz.file.storageservice.dto.UserResponse;
import com.mbenz.file.storageservice.entity.Users;
import com.mbenz.file.storageservice.exception.UserNotFoundException;
import com.mbenz.file.storageservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;

    @Mock
    private FileWriterFactory fileWriterFactory;

    @Mock
    XMLFileWriter xmlFileWriter;
    @Mock
    CSVFileWriter csvFileWriter;

    @Test
    public void getUserDetails_Test() throws JsonProcessingException {
        Users user = new Users("name", "email@email.com", new Long(23232), 3232, new Date());
        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));

        String response = userService.getUserDetails(23, "name", "email");
        Assert.notNull(response);
    }

    @Test
    public void saveUserDetails_Test(){
        UserRequest user = new UserRequest("name", new Date(), new Long(23232), 3232, "email@email.com");
        Mockito.when(fileWriterFactory.getInstance(FileType.CSV)).thenReturn(csvFileWriter);
        //Mockito.when(csvFileWriter.writeToFile(Mockito.any(), Mockito.anyString())).thenReturn(Optional.of("/path"));
        UserResponse response = userService.saveUserDetails(user, FileType.CSV);
        Assert.hasText("name", response.getName());
    }

    @Test(expected = UserNotFoundException.class)
    public void updateUserDetails_NotFound_Test(){
        UserRequest user = new UserRequest("name1", new Date(), new Long(23232), 3232, "email@email.com");
        UserResponse response = userService.updateUserDetails(23, user, FileType.CSV);
    }

    @Test
    public void updateUserDetails_Test(){
        Users user = new Users("name", "email@email.com", new Long(23232), 3232, new Date());
        UserRequest userRequest = new UserRequest("name1", new Date(), new Long(23232), 3232, "email@email.com");
        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
        Mockito.when(fileWriterFactory.getInstance(FileType.CSV)).thenReturn(csvFileWriter);
        //Mockito.when(csvFileWriter.writeToFile(Mockito.any(), Mockito.anyString())).thenReturn(Optional.of("/path"));
        UserResponse response = userService.updateUserDetails(23, userRequest, FileType.CSV);
        Assert.hasText("name1", response.getName());
    }
}
