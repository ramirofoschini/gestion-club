package com.rf.club.dto;

import com.rf.club.model.Plan;
import com.rf.club.model.Relacion;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngresoUpsertRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String documento;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String nombres;

    @NotBlank
    private String equipo;

    @NotNull
    private Relacion relacion;

    @NotNull
    private Plan plan;

    // opcionales
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal importeTransferido;

    private LocalDate fechaTransferencia;
}
