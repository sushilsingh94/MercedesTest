package com.mbenz.file.storageservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbenz.file.storageservice.dto.FileType;
import com.mbenz.file.storageservice.dto.UserRequest;
import com.mbenz.file.storageservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserFileStorageControllerTest {

    @InjectMocks
    UserFileStorageController userFileStorageController;

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userFileStorageController).build();

    }

    @Test
    public void testGetUser() throws Exception{
        mockMvc.perform(get("/v1/users/1")).andExpect(status().isOk());
    }

    @Test
    public void testPostUser() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserRequest userRequest = new UserRequest("name", new Date(), new Long(2323), 23, "email@email.com");
        mockMvc.perform(post("/v1/users").content(objectMapper.writeValueAsString(userRequest))
                        .header("fileType", FileType.CSV)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
}
