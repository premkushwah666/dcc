package com.dcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "submission_files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private Submission submission;

    private String originalFileName;

    private String storedFileName;

    private String fileType;

    private String filePath;

    private Long fileSize;
}
