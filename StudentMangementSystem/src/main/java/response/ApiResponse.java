package com.example.student.response;

public class ApiResponse<T> {

    private final String status;
    private final String message;
    private final T data;

    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

 
    public static <T> ApiResponse<T> of(String status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
