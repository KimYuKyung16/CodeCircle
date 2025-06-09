package com.algorithmsolutionproject.algorithmsolution.security;

import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public record CustomOAuth2User(OAuth2User delegate) implements OAuth2User {
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
        return getEmail();
    }

    public String getEmail() {
        return (String) delegate.getAttribute("email");
    }

    public String getUsername() {
        return (String) delegate.getAttribute("name");
    }
}
