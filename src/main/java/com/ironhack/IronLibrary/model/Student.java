package com.ironhack.IronLibrary.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {
    @Id
    private String usn;
    private String name;

    @OneToMany(mappedBy = "issueStudent",  cascade = CascadeType.ALL)
    private List<Issue> issues;

    public Student() {
    }

    public Student(String usn, String name) {
        this.usn = usn;
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
