package com.algorithmsolutionproject.algorithmsolution.service;

import com.algorithmsolutionproject.algorithmsolution.entity.User;
import com.algorithmsolutionproject.algorithmsolution.repository.UserRepository;
import com.algorithmsolutionproject.algorithmsolution.security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String email = null;
        String name = null;
        String profile = null;

        switch (provider) {
            case "google" -> {
                email = oAuth2User.getAttribute("email");
                name = oAuth2User.getAttribute("name");
                profile = oAuth2User.getAttribute("picture");
            }
            default -> throw new OAuth2AuthenticationException("지원하지 않는 로그인 공급자입니다: " + provider);
        }

        return new CustomOAuth2User(oAuth2User, email, name, profile);
    }
}
