package com.rf.club.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ingresos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String equipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Relacion relacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Plan plan;

    // opcionales
    private BigDecimal importeTransferido;
    private LocalDate fechaTransferencia;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    void prePersist() {
        var now = OffsetDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = OffsetDateTime.now();
    }
}