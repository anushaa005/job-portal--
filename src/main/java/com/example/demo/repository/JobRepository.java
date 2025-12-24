package com.example.demo.repository;

import com.example.demo.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer>{
    Optional<Job> findJobByTitle(String title);
    Optional<Job> findJobById(int id);
    Optional<Job> deleteJobById(int id);
    List<Job> findByTitleContainingIgnoreCase(String keyword);
    List<Job> findByLocationContainingIgnoreCase(String location);
    List<Job> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(
            String keyword,
            String location
    );

    boolean existsByTitle(String title);

    Collection<Object> findJobByLocation(String location);
}
