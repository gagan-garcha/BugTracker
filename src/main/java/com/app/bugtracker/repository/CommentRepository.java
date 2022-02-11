package com.app.bugtracker.repository;

import com.app.bugtracker.model.Comment;
import com.app.bugtracker.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByIssue(Issue issue);
}
