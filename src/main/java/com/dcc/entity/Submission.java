package com.dcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "submitted_by_id", nullable = false)
    private User submittedBy;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private LocalDateTime submittedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubmissionFile> files = new ArrayList<>();

    // Helper method to add file
    public void addFile(SubmissionFile file) {
        files.add(file);
        file.setSubmission(this);
    }

    // Helper method to remove file
    public void removeFile(SubmissionFile file) {
        files.remove(file);
        file.setSubmission(null);
    }
}
