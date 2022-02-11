package com.app.bugtracker.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class IssueEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    private Issue issue;

    @BeforeEach
    public void setUp() {
        issue = Issue.builder().title("New bug").title("Test").issueType(TypeEnum.BUG).status(StatusEnum.NEW).priority(PriorityEnum.MINOR).build();
    }

    @Test
    public  void saveIssueEntity(){
        Issue savedIssue = this.entityManager.persistAndFlush(issue);
        assertEquals(1,savedIssue.getId());
        assertNotNull(savedIssue.getCreatedAt());
        assertNotNull(savedIssue.getUpdatedAt());
    }
}
