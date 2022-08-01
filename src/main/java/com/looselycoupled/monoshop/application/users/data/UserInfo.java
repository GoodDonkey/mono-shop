package com.looselycoupled.monoshop.application.users.data;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfo {
    @Id @GeneratedValue
    private Long id;
    
    private String username;
    private String password;
    private Role role;
}
