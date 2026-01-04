package com.example.produtosapi.bussiness.service;
import com.example.produtosapi.bussiness.dto.UserDTO;
import com.example.produtosapi.bussiness.model.User;
import com.example.produtosapi.bussiness.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(String id){return userRepository.findById(id).orElse(null);}

    public User create(@Valid UserDTO dto){
        User user = dto.toEntity();
        return userRepository.save(user);
    }

    public User update(String id, @Valid UserDTO dto){
        User foundedUser = userRepository.findById(id).orElse(null);
        if(foundedUser != null){
            User updatedUser = dto.toUpdateEntity(foundedUser);
            return userRepository.save(updatedUser);
        }
        return null;
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }

}
