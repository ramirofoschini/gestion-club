package com.rf.club.dto;

import com.rf.club.model.Plan;
import com.rf.club.model.Relacion;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class CertificadoUpsertRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String nombres;

    @NotBlank
    private String documento;
}
