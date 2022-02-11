package com.app.bugtracker.service;

import com.app.bugtracker.model.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Optional<Issue> findIssueById(Long id);

    List<Issue> findAllIssues();

    Issue saveIssue(Issue issue);

    void deleteIssue(Long id);

}
