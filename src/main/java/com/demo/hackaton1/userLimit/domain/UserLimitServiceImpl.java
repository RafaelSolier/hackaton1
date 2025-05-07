package com.demo.hackaton1.userLimit.domain;

import com.demo.hackaton1.user.domain.User;
import com.demo.hackaton1.user.infrastructure.UserRepository;
import com.demo.hackaton1.userLimit.infrastructure.UserLimitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserLimitServiceImpl implements UserLimitService {

    private final UserLimitRepository limitRepo;
    private final UserRepository userRepo;

    public UserLimitServiceImpl(UserLimitRepository limitRepo,
                                UserRepository userRepo) {
        this.limitRepo = limitRepo;
        this.userRepo = userRepo;
    }

    @Override
    public UserLimit assignLimitToUser(Long userId, UserLimit limit) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        limit.setUser(u);
        return limitRepo.save(limit);
    }

    @Override
    public List<UserLimit> getLimitsByUser(Long userId) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return limitRepo.findByUser(u);
    }
}
