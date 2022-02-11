package com.app.bugtracker.service;

import com.app.bugtracker.model.Comment;
import com.app.bugtracker.model.Issue;

import java.util.List;

public interface CommentService {

    Comment findCommentById(Long id);

    Comment saveComment(Comment comment);

    List<Comment> findAllComments(Issue issue);

    void deleteCommentById(Long id);

}
