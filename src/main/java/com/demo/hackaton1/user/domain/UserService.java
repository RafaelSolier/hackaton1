package com.demo.hackaton1.user.domain;

import java.util.List;

public interface UserService {
    User createUser(Long companyId, User user);
    List<User> getUsersByCompany(Long companyId);
    User getUserById(Long id);
    User updateUser(Long id, User details);
}
