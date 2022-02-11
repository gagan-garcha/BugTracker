package com.app.bugtracker.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentDTO {

    @NotNull
    private String body;

    @NotNull
    private String creator;
}
