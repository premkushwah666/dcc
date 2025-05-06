package com.dcc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    @JsonBackReference
    private Assignment assignment;

    private String originalFileName;

    private String storedFileName;

    private String fileType;

    private String filePath;

    private Long fileSize;
}
