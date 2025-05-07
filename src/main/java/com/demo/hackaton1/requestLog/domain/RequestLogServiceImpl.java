package com.demo.hackaton1.requestLog.domain;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.company.infrastructure.CompanyRepository;
import com.demo.hackaton1.requestLog.infrastructure.RequestLogRepository;
import com.demo.hackaton1.user.domain.User;
import com.demo.hackaton1.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RequestLogServiceImpl implements RequestLogService {

    private final RequestLogRepository logRepo;
    private final UserRepository userRepo;
    private final CompanyRepository companyRepo;

    public RequestLogServiceImpl(RequestLogRepository logRepo,
                                 UserRepository userRepo,
                                 CompanyRepository companyRepo) {
        this.logRepo = logRepo;
        this.userRepo = userRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public RequestLog logRequest(RequestLog log) {
        // Asegurar existencia de user y company
        User u = userRepo.findById(log.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found: " + log.getUser().getId()));
        Company c = companyRepo.findById(log.getCompany().getId())
                .orElseThrow(() -> new RuntimeException("Company not found: " + log.getCompany().getId()));
        log.setUser(u);
        log.setCompany(c);
        log.setTimestamp(log.getTimestamp() == null ? LocalDateTime.now() : log.getTimestamp());
        return logRepo.save(log);
    }

    @Override
    public List<RequestLog> getLogsByUser(Long userId) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return logRepo.findByUserOrderByTimestampDesc(u);
    }

    @Override
    public List<RequestLog> getLogsByCompany(Long companyId) {
        Company c = companyRepo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));
        return logRepo.findByCompany(c);
    }
}