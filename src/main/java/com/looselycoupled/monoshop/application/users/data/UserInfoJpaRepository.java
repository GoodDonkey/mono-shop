package com.looselycoupled.monoshop.application.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, UUID> {
    UserInfo findByUsername(String username);
}
