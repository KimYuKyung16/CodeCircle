package com.algorithmsolutionproject.algorithmsolution.security;

public record CustomUserPrincipal(Integer userId, String email, String profile) implements AuthenticatedUser {
}

