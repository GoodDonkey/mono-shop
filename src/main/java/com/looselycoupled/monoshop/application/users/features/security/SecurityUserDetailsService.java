package com.looselycoupled.monoshop.application.users.features.security;

import com.looselycoupled.monoshop.application.users.data.UserInfo;
import com.looselycoupled.monoshop.application.users.data.UserInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserInfoJpaRepository userInfoJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoJpaRepository.findByUsername(username);
        return new SecurityUser(userInfo);
    }
}
