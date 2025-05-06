package com.demo.hackaton1.userLimit.domain;

import com.demo.hackaton1.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_limits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Modelo al que aplica este límite */
    @Column(nullable = false)
    private String modelType;

    /** Máximo de solicitudes en la ventana */
    private Integer maxRequests;

    /** Máximo de tokens en la ventana */
    private Integer maxTokens;

    /** Inicio y fin de la ventana rolling */
    private LocalDateTime windowStart;
    private LocalDateTime windowEnd;

    /** Usuario asociado */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}