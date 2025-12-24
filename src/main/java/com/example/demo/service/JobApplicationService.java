package com.example.demo.service;

import com.example.demo.dto.JobAppRequest;
import com.example.demo.enums.Status;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Job;
import com.example.demo.model.JobApplication;
import com.example.demo.model.User;
import com.example.demo.repository.JobAppRepository;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
@Service
@RequiredArgsConstructor
public class JobApplicationService
{
    private final JobAppRepository jobAppRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ApiResponse<Void> apply(JobAppRequest request)
   {
       Job job = jobRepository.findJobById(request.getJob_id()).orElseThrow(()-> new ResourceNotFoundException("Job does not exist"));
       User user = userRepository.findById(request.getUser_id()).orElseThrow(()-> new ResourceNotFoundException(("User does not exit")));
       if(jobAppRepository.existsByJob(job) && job.getPostedBy().equals(user))
       {
           JobApplication jobApplication = new JobApplication();
           jobApplication.setJob(job);
           jobApplication.setUser(user);
           jobApplication.setStatus(Status.APPLIED);
           jobApplication.setAppliedAt(LocalDateTime.now());
           jobAppRepository.save(jobApplication);
           return ApiResponse.success("Applied to job"+ job.getTitle(),null);
       }
       else
       {
           throw new ResourceNotFoundException("No such job exits");
       }
   }
}
