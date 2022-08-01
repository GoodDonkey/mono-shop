package com.looselycoupled.monoshop.application.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
