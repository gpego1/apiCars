package com.example.produtosapi.bussiness.dto;
import com.example.produtosapi.bussiness.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product toEntity(){
        return Product.builder()
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    public Product toUpdateEntity(Product product) {
        return Product.builder()
                .id(product.getId())
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .createdAt(product.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
