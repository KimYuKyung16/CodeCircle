package com.algorithmsolutionproject.algorithmsolution.security;

public record CustomUserPrincipal(Integer userId, String email) implements AuthenticatedUser {
}

