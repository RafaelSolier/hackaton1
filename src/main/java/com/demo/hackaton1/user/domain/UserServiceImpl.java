package com.demo.hackaton1.user.domain;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.company.infrastructure.CompanyRepository;
import com.demo.hackaton1.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final CompanyRepository companyRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo,
                           CompanyRepository companyRepo,
                           PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.companyRepo = companyRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(Long companyId, User u) {
        Company c = companyRepo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));
        u.setCompany(c);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepo.save(u);
    }

    @Override
    public List<User> getUsersByCompany(Long companyId) {
        Company c = companyRepo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));
        return userRepo.findByCompany(c);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @Override
    public User updateUser(Long id, User details) {
        User u = getUserById(id);
        u.setFullName(details.getFullName());
        u.setEmail(details.getEmail());
        if (details.getPassword() != null) {
            u.setPassword(passwordEncoder.encode(details.getPassword()));
        }
        u.setRole(details.getRole());
        return userRepo.save(u);
    }
}