package com.demo.hackaton1.requestLog.infrastructure;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.requestLog.domain.RequestLog;
import com.demo.hackaton1.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
    // Historial de un usuario, ordenado por fecha descendente
    List<RequestLog> findByUserOrderByTimestampDesc(User user);

    // Historial agregado por empresa
    List<RequestLog> findByCompany(Company company);
}