package com.demo.hackaton1.restriction.infrastructure;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.restriction.domain.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RestrictionRepository extends JpaRepository<Restriction, Long> {
    // Obtener todas las restricciones de una empresa
    List<Restriction> findByCompany(Company company);
}