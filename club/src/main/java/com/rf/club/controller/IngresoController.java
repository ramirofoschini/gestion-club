package com.rf.club.controller;

import com.rf.club.dto.IngresoUpsertRequest;
import com.rf.club.model.Ingreso;
import com.rf.club.repository.IngresoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingresos")
@RequiredArgsConstructor
public class IngresoController {

    private final IngresoRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingreso create(@Valid @RequestBody IngresoUpsertRequest r) {
        var entity = Ingreso.builder()
                .email(r.getEmail())
                .documento(r.getDocumento())
                .apellidos(r.getApellidos())
                .nombres(r.getNombres())
                .equipo(r.getEquipo())
                .relacion(r.getRelacion())
                .plan(r.getPlan())
                .importeTransferido(r.getImporteTransferido())
                .fechaTransferencia(r.getFechaTransferencia())
                .build();

        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public Ingreso update(@PathVariable Long id, @Valid @RequestBody IngresoUpsertRequest r) throws ChangeSetPersister.NotFoundException {
        var e = repo.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        e.setEmail(r.getEmail());
        e.setDocumento(r.getDocumento());
        e.setApellidos(r.getApellidos());
        e.setNombres(r.getNombres());
        e.setEquipo(r.getEquipo());
        e.setRelacion(r.getRelacion());
        e.setPlan(r.getPlan());
        e.setImporteTransferido(r.getImporteTransferido());
        e.setFechaTransferencia(r.getFechaTransferencia());

        return repo.save(e);
    }
    // ✅ GET por DNI
    @GetMapping("/dni/{dni}")
    public Ingreso getByDni(@PathVariable("dni") String dni) throws ChangeSetPersister.NotFoundException {
        return repo.findByDocumento(dni)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}