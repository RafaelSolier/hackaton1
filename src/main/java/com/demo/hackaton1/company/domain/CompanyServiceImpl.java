package com.demo.hackaton1.company.domain;


import com.demo.hackaton1.company.infrastructure.CompanyRepository;
import com.demo.hackaton1.requestLog.domain.RequestLog;
import com.demo.hackaton1.requestLog.infrastructure.RequestLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepo;
    private final RequestLogRepository logRepo;

    public CompanyServiceImpl(CompanyRepository companyRepo,
                              RequestLogRepository logRepo) {
        this.companyRepo = companyRepo;
        this.logRepo = logRepo;
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepo.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }

    @Override
    public Company updateCompany(Long id, Company details) {
        Company c = getCompanyById(id);
        c.setName(details.getName());
        c.setRuc(details.getRuc());
        c.setAffiliationDate(details.getAffiliationDate());
        // no toques active aquí, va en toggleCompanyStatus
        return companyRepo.save(c);
    }

    @Override
    public Company toggleCompanyStatus(Long id, boolean active) {
        Company c = getCompanyById(id);
        c.setActive(active);
        return companyRepo.save(c);
    }

    @Override
    public Map<String, Object> getCompanyConsumption(Long companyId) {
        Company c = getCompanyById(companyId);
        List<RequestLog> logs = logRepo.findByCompany(c);

        long totalRequests = logs.size();
        long totalTokens = logs.stream()
                .filter(l -> l.getTokensUsed() != null)
                .mapToLong(RequestLog::getTokensUsed)
                .sum();

        Map<String, Long> requestsByModel = logs.stream()
                .collect(Collectors.groupingBy(RequestLog::getModelType, Collectors.counting()));

        Map<String, Long> tokensByModel = logs.stream()
                .filter(l -> l.getTokensUsed() != null)
                .collect(Collectors.groupingBy(RequestLog::getModelType,
                        Collectors.summingLong(RequestLog::getTokensUsed)));

        Map<String, Object> report = new HashMap<>();
        report.put("totalRequests", totalRequests);
        report.put("totalTokens", totalTokens);
        report.put("requestsByModel", requestsByModel);
        report.put("tokensByModel", tokensByModel);
        return report;
    }
}