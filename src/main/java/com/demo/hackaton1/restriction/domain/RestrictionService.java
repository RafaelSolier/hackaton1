package com.demo.hackaton1.restriction.domain;

import java.util.List;

public interface RestrictionService {
    Restriction createRestriction(Long companyId, Restriction restriction);
    List<Restriction> getRestrictionsByCompany(Long companyId);
    Restriction updateRestriction(Long id, Restriction details);
    void deleteRestriction(Long id);
}