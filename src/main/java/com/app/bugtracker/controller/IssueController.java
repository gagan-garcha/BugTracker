package com.app.bugtracker.controller;

import com.app.bugtracker.exceptions.ResourceNotFoundException;
import com.app.bugtracker.model.*;
import com.app.bugtracker.model.dto.CommentDTO;
import com.app.bugtracker.model.dto.IssueDTO;
import com.app.bugtracker.service.CommentService;
import com.app.bugtracker.service.IssueService;
import com.app.bugtracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(IssueController.BASE_URL)
public class IssueController {

    public static final String BASE_URL = "/api/v1/issues";

    private final IssueService mIssueService;

    private final UserService mUserService;

    private final CommentService mCommentService;

    public IssueController(IssueService mIssueService, UserService mUserService, CommentService mCommentService) {
        this.mIssueService = mIssueService;
        this.mUserService = mUserService;
        this.mCommentService = mCommentService;
    }


    @GetMapping()
    public List<Issue> getAllIssues() {
        return  mIssueService.findAllIssues();
    }

    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable Long id) {
        return  mIssueService.findIssueById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Issue"));
    }



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Issue addIssue(
                          @RequestBody @Valid IssueDTO issueDetails,
                          @RequestParam(name = "priority", defaultValue = "MAJOR", required = false) PriorityEnum priority,
                          @RequestParam(name = "kind", defaultValue = "BUG", required = false) TypeEnum issueType
                          ) {

        Issue issue =  Issue.builder()
                .title(issueDetails.getTitle())
                .assignee(mUserService.findUserByUsername(issueDetails.getAssignee()).orElseThrow(()-> new ResourceNotFoundException("Invalid User")))
                .description(issueDetails.getDescription())
                .status(StatusEnum.NEW)
                .creator(mUserService.findUserByUsername(issueDetails.getCreator()).orElseThrow(()-> new ResourceNotFoundException("Invalid User")))
        .build();
        if (priority == null) {
            issue.setPriority(PriorityEnum.MAJOR);
        } else {
            issue.setPriority(priority);
        }
        if (issueType == null) {
            issue.setIssueType(TypeEnum.BUG);
        } else {
            issue.setIssueType(issueType);
        }

        return mIssueService.saveIssue(issue);
    }


    @PatchMapping("/{id}")
    public Issue updateIssue(@PathVariable("id") Long id,
                             @RequestParam(name = "priority", required = false) PriorityEnum priority,
                             @RequestParam(name = "kind", required = false) TypeEnum issueType,
                             @RequestParam(name = "status", required = false) StatusEnum status,
                             @RequestBody(required = false) IssueDTO issueDetails){
        Issue issueMod = mIssueService.findIssueById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Issue"));
        if (issueDetails != null) {
            if (issueDetails.getTitle() != null && !issueDetails.getTitle().equals(issueMod.getTitle())) {
                issueMod.setTitle(issueDetails.getTitle());
            }
            if (issueDetails.getDescription() != null && !issueDetails.getDescription().equals(issueMod.getDescription())){
                issueMod.setDescription(issueDetails.getDescription());
            }
            User user = mUserService.findUserByUsername(issueDetails.getAssignee()).orElse(null);
            if (issueDetails.getAssignee() != null && user != issueMod.getAssignee()) {
                issueMod.setAssignee(user);
            }
            if (issueType != null && issueType != issueMod.getIssueType()) {
                issueMod.setIssueType(issueType);

            }
            if (priority != null && priority != issueMod.getPriority()) {
                issueMod.setPriority(priority);
            }
            if (status != null && status != issueMod.getStatus()){
                issueMod.setStatus(status);
            }

        }
        return mIssueService.saveIssue(issueMod);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIssue(@PathVariable("id") Long id) {

        List<Comment> comments = mCommentService.findAllComments(mIssueService.findIssueById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Issue")));
        for (Comment c : comments) {
            mCommentService.deleteCommentById(c.getId());
        }


        mIssueService.deleteIssue(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getAllComments(@PathVariable Long id) {
        return mCommentService.findAllComments(mIssueService.findIssueById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Issue")));
    }


    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@PathVariable("id") Long id,
                              @RequestBody CommentDTO commentDTO) {


        Comment comment = Comment.builder()
                .issue(mIssueService.findIssueById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Issue")))
                .body(commentDTO.getBody())
                .creator(mUserService.findUserByUsername(commentDTO.getCreator()).orElseThrow(()-> new ResourceNotFoundException("Invalid User")))
                .build();

        return mCommentService.saveComment(comment);
    }


    @DeleteMapping("/{id}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("id") Long id,
                              @PathVariable("commentId") Long commentId) {
        Comment comment = mCommentService.findCommentById(commentId);
        mCommentService.deleteCommentById(comment.getId());
    }
}
