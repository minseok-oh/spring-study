package com.java.study.storage.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Lob
    @Column
    private byte[] content;

    public File() {

    }

    public File(Long id, String name, byte[] content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
