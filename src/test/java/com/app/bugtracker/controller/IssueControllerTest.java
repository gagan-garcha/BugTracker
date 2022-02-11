package com.app.bugtracker.controller;


import com.app.bugtracker.model.*;
import com.app.bugtracker.service.CommentService;
import com.app.bugtracker.service.IssueService;
import com.app.bugtracker.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class IssueControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    @Mock
    private  IssueService mIssueService;

    @Autowired
    @Mock
    private  UserService mUserService;

    @Autowired
    @Mock
    private  CommentService mCommentService;

    @InjectMocks
    private IssueController mIssueController;

    private Issue issue;

    private User user;

    @BeforeEach
    public void setUp() {
        issue = Issue.builder().title("New bug").title("Test").issueType(TypeEnum.BUG).status(StatusEnum.NEW).priority(PriorityEnum.MINOR).build();
        user = User.builder().username("test").photoUrl("asdfdf").build();
        mockMvc = MockMvcBuilders.standaloneSetup(mIssueController).build();
    }

    @Test
    public void PostMappingForIssue() throws Exception {
        when(mIssueService.saveIssue(any())).thenReturn(issue);
        when(mUserService.findUserByUsername(any())).thenReturn(Optional.of(user));
        mockMvc.perform(post("/api/v1/issues").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(issue))).
                andExpect(status().isCreated());

        verify(mIssueService,times(1)).saveIssue(any());
    }

    @Test
    public void invalidPostMappingForIssue() throws Exception {
        mockMvc.perform(post("/api/v1/issues").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(issue))).
                andExpect(MockMvcResultMatchers.status().isBadRequest()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public  void GetMappingForIssue() throws  Exception{
        when(mIssueService.findIssueById(any())).thenReturn(Optional.of(issue));
        mockMvc.perform(get("/api/v1/issues/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(issue)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public  void GetMappingForAllIssue() throws  Exception{
        when(mIssueService.findAllIssues()).thenReturn(List.of(issue));
        mockMvc.perform(get("/api/v1/issues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(issue)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
