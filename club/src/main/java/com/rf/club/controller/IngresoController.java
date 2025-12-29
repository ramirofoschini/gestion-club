package com.rf.club.controller;

import com.rf.club.dto.IngresoUpsertRequest;
import com.rf.club.model.Ingreso;
import com.rf.club.repository.IngresoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ingresos")
@RequiredArgsConstructor
public class IngresoController {

    private final IngresoRepository repo;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Ingreso ingreso) {

        if (repo.existsByDocumento(ingreso.getDocumento())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409
                    .body(Map.of(
                            "message", "Ya existe un usuario con DNI " + ingreso.getDocumento()
                    ));
        }

        Ingreso guardado = repo.save(ingreso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
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
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Map<String, Object>> getByDni(@PathVariable String dni) {
        return repo.findByDocumento(dni)
                .map(u -> ResponseEntity.ok(Map.of(
                        "found", true,
                        "data", u
                )))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "found", false,
                        "message", "No se encontró usuario con DNI " + dni
                )));
    }

}