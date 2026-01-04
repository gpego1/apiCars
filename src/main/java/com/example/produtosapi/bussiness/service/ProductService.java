package com.example.produtosapi.bussiness.service;
import com.example.produtosapi.bussiness.dto.ProductDTO;
import com.example.produtosapi.bussiness.model.Product;
import com.example.produtosapi.bussiness.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Product findById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(ProductDTO dto) {
        Product product = dto.toEntity();
        return productRepository.save(product);
    }

    @Transactional
    public Product update(String id, ProductDTO dto) {
        Product foundedProduct = productRepository.findById(id).orElse(null);
        if(foundedProduct != null){
            Product updatedProduct = dto.toUpdateEntity(foundedProduct);
            return productRepository.save(updatedProduct);
        }
        return null;
    }

    public void delete(String id) {
        productRepository.deleteById(id);

    }

}
