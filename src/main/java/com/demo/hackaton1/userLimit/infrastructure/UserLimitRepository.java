package com.demo.hackaton1.userLimit.infrastructure;

import com.demo.hackaton1.user.domain.User;
import com.demo.hackaton1.userLimit.domain.UserLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {
    // Límites de un usuario específico
    List<UserLimit> findByUser(User user);
}