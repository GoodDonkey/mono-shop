package com.looselycoupled.monoshop.users.data;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfo {
    @Id
    @Type(type = "uuid-char")
    private UUID userId;
    
    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
