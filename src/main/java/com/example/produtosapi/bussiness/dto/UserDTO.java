package com.example.produtosapi.bussiness.dto;

import com.example.produtosapi.bussiness.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;
    @Email private String username;
    private String password;
    @NotNull private Integer age;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .age(this.age)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }
    public User toUpdateEntity(User user) {
        return User.builder()
                .id(user.getId())
                .username(this.username)
                .password(this.password)
                .age(this.age)
                .createdDate(user.getCreatedDate())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }
}
