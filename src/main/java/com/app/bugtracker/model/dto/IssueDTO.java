package com.app.bugtracker.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueDTO {

    private String title;

    private String assignee;

    private String description;

    private String creator;

}
