package com.example.produtosapi.bussiness.repository;
import com.example.produtosapi.bussiness.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
