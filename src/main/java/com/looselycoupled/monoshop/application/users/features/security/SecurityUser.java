package com.looselycoupled.monoshop.application.users.features.security;

import com.looselycoupled.monoshop.application.users.data.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUser extends User {
    
    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    
    public SecurityUser(UserInfo userInfo) {
        super(userInfo.getUsername(),
              userInfo.getPassword(),
              AuthorityUtils.createAuthorityList(userInfo.getRole().toString()));
    }
}
