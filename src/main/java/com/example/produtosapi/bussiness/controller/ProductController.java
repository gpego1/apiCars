package com.example.produtosapi.bussiness.controller;
import com.example.produtosapi.bussiness.dto.ProductDTO;
import com.example.produtosapi.bussiness.model.Product;
import com.example.produtosapi.bussiness.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Product> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Product findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
        Product createdProduct = service.create(dto);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();
        headers.setLocation(location);

        return new ResponseEntity<>(createdProduct, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Product update(@PathVariable String id, @RequestBody ProductDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}
