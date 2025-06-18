package com.algorithmsolutionproject.algorithmsolution.security;

import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public record CustomOAuth2User(
        OAuth2User delegate,
        String email,
        String userName,
        String profile
) implements OAuth2User {

    @Override
    public Map<String, Object> getAttributes() {
        return delegate.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return delegate.getAuthorities();
    }

    @Override
    public String getName() {
        return email; // OAuth2User.getName()이 요구하는 고유 식별자
    }
}
