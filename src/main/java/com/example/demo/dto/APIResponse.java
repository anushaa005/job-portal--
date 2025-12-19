package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T>
{
    private boolean status;
    private String message;
    private T data;

    public static <T> APIResponse<T> success( String message, T data)
    {
        return new APIResponse<>(true, message, data );
    }

    public static <T> APIResponse <T> failure( String message)
    {
        return new APIResponse<>(false, message, null);
    }

}
