package com.demo.hackaton1.user.infrastructure;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.user.domain.Role;
import com.demo.hackaton1.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Para autenticación / login
    Optional<User> findByEmail(String email);

    // Listar todos los usuarios de una empresa
    List<User> findByCompany(Company company);

    // Listar todos los usuarios por rol
    List<User> findByRole(Role role);
}
