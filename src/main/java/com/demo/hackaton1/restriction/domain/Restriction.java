package com.demo.hackaton1.restriction.domain;

import com.demo.hackaton1.company.domain.Company;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restrictions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Tipo de modelo (ej. "openai-chat", "meta-vision", etc.) */
    @Column(nullable = false)
    private String modelType;

    /** Límite de peticiones por ventana (empresa) */
    private Integer requestLimit;

    /** Límite de tokens por ventana (empresa) */
    private Integer tokenLimit;

    /** Duración de la ventana rolling en minutos */
    private Integer windowMinutes;

    /** Pertenencia a la empresa */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}