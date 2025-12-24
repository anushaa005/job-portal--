package com.example.demo.repository;

import com.example.demo.model.Job;
import com.example.demo.model.JobApplication;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobAppRepository extends JpaRepository<JobApplication,Integer> {
    Optional<JobApplication> findJobApplicationByJob(Job job);

    Optional<JobApplication> findJobApplicationByUser(User user);

    boolean existsByJob(Job job);

    boolean existsByUser(User user);
}
