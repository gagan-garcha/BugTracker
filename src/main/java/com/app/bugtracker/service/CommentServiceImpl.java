package com.app.bugtracker.service;

import com.app.bugtracker.model.Comment;
import com.app.bugtracker.model.Issue;
import com.app.bugtracker.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    CommentRepository mCommentRepository;

    public CommentServiceImpl(CommentRepository mCommentRepository) {
        this.mCommentRepository = mCommentRepository;
    }

    @Override
    public Comment findCommentById(Long id) {
        return mCommentRepository.findById(id).get();
    }

    @Override
    public Comment saveComment(Comment comment) {
        return mCommentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllComments(Issue issue) {
        return mCommentRepository.findAllByIssue(issue);
    }

    @Override
    public void deleteCommentById(Long id) {
        mCommentRepository.deleteById(id);
    }
}
