package com.dcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assignment_files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    private String originalFileName;

    private String storedFileName;

    private String fileType;

    private String filePath;

    private Long fileSize;
}
