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
public class CommentEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = Comment.builder().body("test").build();
    }

    @Test
    public  void saveCommentEntity(){
        Comment savedComment = this.entityManager.persistAndFlush(comment);
        assertEquals(1,savedComment.getId());
        assertNotNull(savedComment.getCreatedAt());
        assertNotNull(savedComment.getUpdatedAt());
    }
}
