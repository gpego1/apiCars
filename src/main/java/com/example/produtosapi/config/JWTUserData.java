package com.example.produtosapi.config;

import lombok.Builder;

@Builder
public record JWTUserData(String userId, String email) {
}
