package com.app.bugtracker.repository;

import com.app.bugtracker.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    private Issue issue;

    @BeforeEach
    public void setUp() {
        issue = Issue.builder().title("New bug").title("Test").issueType(TypeEnum.BUG).status(StatusEnum.NEW).priority(PriorityEnum.MINOR).build();
    }



    @Test
    public void findById(){
        Issue savedIssue = issueRepository.save(issue);
        assertEquals(savedIssue.getId(),issueRepository.findById(savedIssue.getId()).get().getId());
    }

    @Test
    public void findAll(){
        issueRepository.save(issue);
        List<Issue> listIssues =  issueRepository.findAll();

        assertEquals(1,listIssues.size());
    }

    @Test
    public void deleteIssue(){
        Issue savedIssue = issueRepository.save(issue);

         issueRepository.deleteById(savedIssue.getId());


        assertEquals(0,issueRepository.findAll().size());
    }
}
