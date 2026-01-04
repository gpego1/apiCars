package com.example.produtosapi.bussiness.controller;
import com.example.produtosapi.bussiness.dto.request.LoginRequest;
import com.example.produtosapi.bussiness.dto.request.RegisterUserRequest;
import com.example.produtosapi.bussiness.dto.response.LoginResponse;
import com.example.produtosapi.bussiness.dto.response.RegisterResponse;
import com.example.produtosapi.bussiness.model.User;
import com.example.produtosapi.bussiness.repository.UserRepository;
import com.example.produtosapi.config.TokenConfig;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          TokenConfig tokenConfig
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }

     @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterUserRequest request){
        User createdUser = new User();
        createdUser.setEmail(request.email());
        createdUser.setPassword(passwordEncoder.encode(request.password()));
        createdUser.setName(request.name());
        createdUser.setAge(request.age());
        createdUser.setCreatedDate(LocalDateTime.now());
        createdUser.setLastModifiedDate(LocalDateTime.now());
        createdUser.setRole(User.roles.CUSTOMER);
        userRepository.save(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(createdUser.getName(),  createdUser.getEmail(),createdUser.getAge()));
    }

}
