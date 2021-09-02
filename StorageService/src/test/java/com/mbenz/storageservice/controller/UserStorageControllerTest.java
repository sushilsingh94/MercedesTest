package com.mbenz.storageservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbenz.storageservice.dto.FileType;
import com.mbenz.storageservice.dto.UserRequest;
import com.mbenz.storageservice.dto.UserResponse;
import com.mbenz.storageservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserStorageControllerTest {

    @InjectMocks
    UserStorageController userStorageController;

    @Mock
    UserService userService;
    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userStorageController).build();

    }

    @Test
    public void testGetUser() throws Exception{
        mockMvc.perform(get("/v1/users/1")).andExpect(status().isOk());
    }

    @Test
    public void testPostUser() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserRequest userRequest = new UserRequest("name", new Date(), new Long(23232), 3232, "email@email.com");
        Mockito.when(userService.saveUserDetails(Mockito.any(), Mockito.any())).thenReturn(new UserResponse());
        mockMvc.perform(post("/v1/users").content(objectMapper.writeValueAsString(userRequest))
                        .param("id", String.valueOf(23))
                .header("fileType", FileType.CSV)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void testPUTUser() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserRequest userRequest = new UserRequest("name", new Date(), new Long(23232), 3232, "email@email.com");
        Mockito.when(userService.updateUserDetails(Mockito.anyInt(), Mockito.any(), Mockito.any())).thenReturn(new UserResponse());
        mockMvc.perform(post("/v1/users/{id}").content(objectMapper.writeValueAsString(userRequest))
                .header("fileType", FileType.CSV)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
}
