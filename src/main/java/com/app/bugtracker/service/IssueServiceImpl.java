package com.app.bugtracker.service;

import com.app.bugtracker.model.Issue;
import com.app.bugtracker.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService{

    private final IssueRepository mIssueRepository;

    public IssueServiceImpl(IssueRepository mIssueRepository) {
        this.mIssueRepository = mIssueRepository;
    }

    @Override
    public Optional<Issue> findIssueById(Long id) {
        return mIssueRepository.findById(id);
    }

    @Override
    public List<Issue> findAllIssues() {
        return mIssueRepository.findAll();
    }

    @Override
    public Issue saveIssue(Issue issue) {
        return mIssueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long id) {
        mIssueRepository.deleteById(id);
    }

}
