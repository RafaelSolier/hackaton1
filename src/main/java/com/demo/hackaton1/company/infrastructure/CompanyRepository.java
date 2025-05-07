package com.demo.hackaton1.company.infrastructure;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // Buscar empresas por estado (activas/inactivas)
    List<Company> findByActive(boolean active);

    // Buscar la empresa cuyo administrador principal es el usuario dado
    Company findByAdmin(User admin);
}