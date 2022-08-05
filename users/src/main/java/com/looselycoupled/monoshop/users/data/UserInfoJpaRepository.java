package com.looselycoupled.monoshop.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, UUID> {
    UserInfo findByUsername(String username);
}
