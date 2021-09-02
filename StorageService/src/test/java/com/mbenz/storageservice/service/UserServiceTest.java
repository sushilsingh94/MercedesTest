package com.mbenz.storageservice.service;

import com.mbenz.storageservice.dto.FileType;
import com.mbenz.storageservice.dto.HttpUserResponse;
import com.mbenz.storageservice.dto.UserRequest;
import com.mbenz.storageservice.dto.UserResponse;
import com.mbenz.storageservice.exception.UserNotFoundException;
import com.mbenz.storageservice.kafka.KafKaProducerService;
import com.mbenz.storageservice.utils.AESUtil;
import com.mbenz.storageservice.utils.HttpUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.http.HttpResponse;
import java.util.Date;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpUtils.class, AESUtil.class})
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    KafKaProducerService kafKaProducerService;
    @Mock
    HttpResponse httpResponse;
    @Mock
    String servieBasepath;

    @Before
    public void before(){
        PowerMockito.mockStatic(HttpUtils.class);
        PowerMockito.mockStatic(AESUtil.class);
    }
    @Test(expected = UserNotFoundException.class)
    public void testGetUser_Exception() throws Exception {
        PowerMockito.when(HttpUtils.makeGetCall(Mockito.anyString())).thenReturn(httpResponse);
        PowerMockito.when(AESUtil.decrypt(Mockito.anyString())).thenReturn("ashdka22342");
        userService.getUserDetails(23, "name", "email");
    }

    @Test
    public void testGetUser() throws Exception {
        PowerMockito.when(HttpUtils.makeGetCall(Mockito.anyString())).thenReturn(httpResponse);
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body()).thenReturn("result");
        PowerMockito.when(AESUtil.decrypt(Mockito.anyString())).thenReturn("{\"name\":\"Sushilvm\",\"email\":\"sushil@gmail.com\",\"salary\":234,\"age\":23,\"dob\":\"2021-08-29\"}");
        HttpUserResponse userResponse = userService.getUserDetails(23, "name", "email");
        Assert.assertEquals(200, userResponse.getStatusCode());
    }

    @Test
    public void testCreateUser() throws Exception {
        UserRequest userRequest = new UserRequest("Sushilvm", new Date(), new Long(23232), 3232, "email@email.com");
        Mockito.doNothing().when(kafKaProducerService).sendMessage(Mockito.anyString());
        PowerMockito.when(AESUtil.encrypt(Mockito.anyString())).thenReturn("{\"name\":\"Sushilvm\",\"email\":\"sushil@gmail.com\",\"salary\":234,\"age\":23,\"dob\":\"2021-08-29\"}");
        UserResponse userResponse = userService.saveUserDetails(userRequest, FileType.CSV);
        Assert.assertEquals("Sushilvm", userResponse.getName());
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserRequest userRequest = new UserRequest("Sushilvm", new Date(), new Long(23232), 3232, "email@email.com");
        Mockito.doNothing().when(kafKaProducerService).sendMessage(Mockito.anyString());
        PowerMockito.when(AESUtil.encrypt(Mockito.anyString())).thenReturn("{\"name\":\"Sushilvm\",\"email\":\"sushil@gmail.com\",\"salary\":234,\"age\":23,\"dob\":\"2021-08-29\"}");
        UserResponse userResponse = userService.updateUserDetails(23, userRequest, FileType.CSV);
        Assert.assertEquals("Sushilvm", userResponse.getName());
    }
}
