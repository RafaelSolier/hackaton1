package com.demo.hackaton1.requestLog.domain;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Modelo usado */
    @Column(nullable = false)
    private String modelType;

    /** Texto de la consulta */
    @Lob
    @Column(nullable = false)
    private String queryText;

    /** Respuesta u error */
    @Lob
    private String responseText;

    /** Tokens consumidos */
    private Integer tokensUsed;

    /** Timestamp de la petición */
    @Column(nullable = false)
    private LocalDateTime timestamp;

    /** Nombre de archivo si hubo componente multimodal */
    private String fileName;

    /** Relaciones */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
