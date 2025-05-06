package com.example.hack_1.company.domain;

import com.example.hack_1.requestLog.domain.RequestLog;
import com.example.hack_1.restriction.domain.Restriction;
import com.example.hack_1.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Información de empresa */
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String ruc;

    @Column(name = "affiliation_date", nullable = false)
    private LocalDate affiliationDate;

    @Column(nullable = false)
    private boolean active = true;

    /** Administrador principal de la empresa */
    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private User admin;

    /** Restricciones definidas a nivel de empresa */
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restriction> restrictions;

    /** Usuarios finales de la empresa */
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    /** Historial agregado de todas las solicitudes de la empresa */
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestLog> requestLogs;
}