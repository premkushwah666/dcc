package com.dcc.entity;

import com.dcc.enums.Enumiration;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate deadline;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AssignmentFile> files = new ArrayList<>();



   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

//    @Enumerated(EnumType.STRING)
//    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Enumiration.AssignmentStatus status = Enumiration.AssignmentStatus.PENDING;

    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private User assignedToUser;

//    @ManyToOne
//    @JoinColumn(name = "assigned_group_id")
//    private Group assignedToGroup;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    private LocalDateTime completedAt;

    // For storing tags
    private String tags;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignmentFile> files = new ArrayList<>();

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Submission> submissions = new ArrayList<>();

    // Helper method to add file
    public void addFile(AssignmentFile file) {
        files.add(file);
        file.setAssignment(this);
    }

    // Helper method to remove file
    public void removeFile(AssignmentFile file) {
        files.remove(file);
        file.setAssignment(null);
    }

    // Helper method to add submission
    public void addSubmission(Submission submission) {
        submissions.add(submission);
        submission.setAssignment(this);
    }*/
}
