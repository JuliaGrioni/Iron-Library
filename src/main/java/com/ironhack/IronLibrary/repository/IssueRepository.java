package com.ironhack.IronLibrary.repository;

import com.ironhack.IronLibrary.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
    List<Issue> findByIssueStudentUsn(String usn);
}
