package com.demo.hackaton1.user.domain;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.requestLog.domain.RequestLog;
import com.demo.hackaton1.userLimit.domain.UserLimit;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Datos básicos */
    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    /** ROLE_SPARKY_ADMIN, ROLE_COMPANY_ADMIN o ROLE_USER */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /** Relación con empresa (null si es SPARKY_ADMIN) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    /** Límites específicos del usuario */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLimit> limits;

    /** Historial de peticiones del usuario */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestLog> requestLogs;
}