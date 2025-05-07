package com.demo.hackaton1.restriction.domain;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.company.infrastructure.CompanyRepository;
import com.demo.hackaton1.restriction.infrastructure.RestrictionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RestrictionServiceImpl implements RestrictionService {

    private final RestrictionRepository restrRepo;
    private final CompanyRepository companyRepo;

    public RestrictionServiceImpl(RestrictionRepository restrRepo,
                                  CompanyRepository companyRepo) {
        this.restrRepo = restrRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public Restriction createRestriction(Long companyId, Restriction r) {
        Company c = companyRepo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));
        r.setCompany(c);
        return restrRepo.save(r);
    }

    @Override
    public List<Restriction> getRestrictionsByCompany(Long companyId) {
        Company c = companyRepo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));
        return restrRepo.findByCompany(c);
    }

    @Override
    public Restriction updateRestriction(Long id, Restriction details) {
        Restriction r = restrRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restriction not found: " + id));
        r.setModelType(details.getModelType());
        r.setRequestLimit(details.getRequestLimit());
        r.setTokenLimit(details.getTokenLimit());
        r.setWindowMinutes(details.getWindowMinutes());
        return restrRepo.save(r);
    }

    @Override
    public void deleteRestriction(Long id) {
        Restriction r = restrRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restriction not found: " + id));
        restrRepo.delete(r);
    }
}