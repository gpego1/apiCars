package com.example.produtosapi.bussiness.repository;
import com.example.produtosapi.bussiness.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<UserDetails> findUserByEmail(String username);
}
