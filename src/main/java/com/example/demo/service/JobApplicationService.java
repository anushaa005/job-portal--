package com.example.demo.service;

import com.example.demo.dto.JobAppRequest;
import com.example.demo.dto.JobAppResponse;
import com.example.demo.enums.Status;
import com.example.demo.exception.InvalidApplicationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Job;
import com.example.demo.model.JobApplication;
import com.example.demo.model.User;
import com.example.demo.repository.JobAppRepository;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.SortDirection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
       Job job = jobRepository.findJobById(request.getJobId()).orElseThrow(()-> new ResourceNotFoundException("Job does not exist"));
       User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new ResourceNotFoundException(("User does not exit")));
       if(job.getPostedBy().equals(user))
       {
           throw new InvalidApplicationRequest("You cannnot apply to your own job posting");
       }
       if(jobAppRepository.existsByJobAndUser(job,user))
       {
           throw new IllegalStateException("You have already applied to this job");
       }


           JobApplication jobApplication = new JobApplication();
           jobApplication.setJob(job);
           jobApplication.setUser(user);
           jobApplication.setStatus(Status.APPLIED);
           jobApplication.setAppliedAt(LocalDateTime.now());
           jobAppRepository.save(jobApplication);
           return ApiResponse.success("Applied to "+ job.getTitle(),null);
   }

     public ApiResponse<List<JobAppResponse>> getApplicationByUser(int userId)
   {
    User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User does not exist"));
    List<JobAppResponse> jobApplications = jobAppRepository.findAllByUser(user)
            .stream()
            .map(this::mapToResponse)
            .toList();
       return ApiResponse.success("Applications by USER "+ userId, jobApplications);

    }
    public JobAppResponse mapToResponse(JobApplication jobApplication)
    {
        JobAppResponse jobAppResponse = new JobAppResponse();
        jobAppResponse.setJob(jobApplication.getJob());
        jobAppResponse.setUser(jobApplication.getUser());
        jobAppResponse.setStatus(jobApplication.getStatus());
        jobAppResponse.setAppliedAt(jobApplication.getAppliedAt());
        return jobAppResponse;
    }

    public ApiResponse<Page<JobAppResponse>> getApplicationByJob(int jobId, int page, int size, String sortBy, String direction)
    {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection,sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        Job job = jobRepository.findJobById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job does not exist"));
        Page<JobAppResponse> jobApplications = jobAppRepository.findAllByJob(job, pageable)
                .map(this::mapToResponse);

        return ApiResponse.success("Applications for JOB "+ jobId, jobApplications);
    }

    public ApiResponse<Void> updateApplicationStatus(int applicationId, Status status)
    {
        JobApplication jobApplication = jobAppRepository.findJobApplicationById(applicationId).orElseThrow(()-> new ResourceNotFoundException("Application not found"));
        jobApplication.setStatus(status);
        jobAppRepository.save(jobApplication);
        return ApiResponse.success("Application status updated",null);
    }


}
