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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String body;

    @ManyToOne
    @JoinColumn
    private User creator;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Issue issue;

    @Column(updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdAt;

    @UpdateTimestamp
    @JsonIgnore
    private Timestamp updatedAt;
}
