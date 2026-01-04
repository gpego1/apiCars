package com.example.produtosapi.bussiness.controller;
import com.example.produtosapi.bussiness.dto.UserDTO;
import com.example.produtosapi.bussiness.model.User;
import com.example.produtosapi.bussiness.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public User getUser(@PathVariable String id) {
        return service.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO dto) {
        User user = service.create(dto);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public User updateUser(@PathVariable String id, @Valid @RequestBody UserDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        service.deleteById(id);
    }
}
