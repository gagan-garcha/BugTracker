package com.app.bugtracker.service;

import com.app.bugtracker.model.Issue;
import com.app.bugtracker.model.PriorityEnum;
import com.app.bugtracker.model.StatusEnum;
import com.app.bugtracker.model.TypeEnum;
import com.app.bugtracker.repository.IssueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IssueServiceTest {

    @Mock
    private IssueRepository mIssueRepository;

    @InjectMocks
    @Autowired
    private IssueServiceImpl mIssueService;

    private Issue issue;

    @BeforeEach
    public void setUp() {
        issue = Issue.builder().title("New bug").title("Test").issueType(TypeEnum.BUG).status(StatusEnum.NEW).priority(PriorityEnum.MINOR).build();
    }

    @Test
    public void givenIssueToAddShouldReturnIssue(){
        when(mIssueRepository.save(any())).thenReturn(issue);
        mIssueService.saveIssue(issue);
        verify(mIssueRepository,times(1)).save(any());
    }

}
