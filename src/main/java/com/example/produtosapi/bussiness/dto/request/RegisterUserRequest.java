package com.example.produtosapi.bussiness.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequest(
        @NotEmpty(message = "Name is required") String name,
        @NotEmpty(message = "Email is required") @Email String email,
        @NotEmpty(message = "Password is required") String password,
        @NotNull(message = "Age is required") @Min(value = 18, message = "You must be 18 years old at least") Integer age
) {
}
