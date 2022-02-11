package com.app.bugtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @ManyToOne
    private User assignee;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TypeEnum issueType;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private PriorityEnum priority;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;

    private String description;

    @ManyToOne
    private User creator;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonIgnore
    Timestamp createdAt;

    @UpdateTimestamp
    @JsonIgnore
    Timestamp updatedAt;

}
