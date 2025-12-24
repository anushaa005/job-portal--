package com.example.demo.controller;

import com.example.demo.dto.JobAppRequest;
import com.example.demo.model.JobApplication;
import com.example.demo.repository.JobRepository;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app")
@RequiredArgsConstructor
public class JobApplicationController
{
    private final JobApplicationService jobApplicationService;
    private final JobRepository jobRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> apply(JobAppRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobApplicationService.apply(request));
    }

}
