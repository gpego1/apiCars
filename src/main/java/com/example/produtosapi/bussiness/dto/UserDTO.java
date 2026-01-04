package com.example.produtosapi.bussiness.dto;

import com.example.produtosapi.bussiness.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String name;
    @Email @Indexed(unique = true) private String email;
    private String password;
    @NotNull private Integer age;
    private User.roles role;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .age(this.age)
                .role(this.role)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }
    public User toUpdateEntity(User user) {
        return User.builder()
                .id(user.getId())
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .age(this.age)
                .role(user.getRole())
                .createdDate(user.getCreatedDate())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }
}
