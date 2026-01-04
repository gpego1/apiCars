package com.example.produtosapi.bussiness.repository;
import com.example.produtosapi.bussiness.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
