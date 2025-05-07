package com.demo.hackaton1.company.domain;

import java.util.List;
import java.util.Map;

public interface CompanyService {
    Company createCompany(Company company);
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    Company updateCompany(Long id, Company companyDetails);
    Company toggleCompanyStatus(Long id, boolean active);
    /**
     * Retorna un mapa con:
     *  - totalRequests: número total de requests
     *  - totalTokens: suma de tokens usados
     *  - requestsByModel: mapa modelo→count
     *  - tokensByModel: mapa modelo→tokens
     */
    Map<String, Object> getCompanyConsumption(Long companyId);
}
