package com.demo.hackaton1.userLimit.domain;

import java.util.List;

public interface UserLimitService {
    UserLimit assignLimitToUser(Long userId, UserLimit limit);
    List<UserLimit> getLimitsByUser(Long userId);
}