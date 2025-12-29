package com.rf.club.controller;

import com.rf.club.dto.CertificadoUpsertRequest;
import com.rf.club.model.Certificado;
import com.rf.club.repository.CertificadoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificados")
@RequiredArgsConstructor
public class CertificadoController {

    private final CertificadoRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Certificado create(@Valid @RequestBody CertificadoUpsertRequest r) {
        var entity = Certificado.builder()
                .email(r.getEmail())
                .apellidos(r.getApellidos())
                .nombres(r.getNombres())
                .documento(r.getDocumento())
                .build();

        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public Certificado update(@PathVariable Long id, @Valid @RequestBody CertificadoUpsertRequest r) throws ChangeSetPersister.NotFoundException {
        var c = repo.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        c.setEmail(r.getEmail());
        c.setApellidos(r.getApellidos());
        c.setNombres(r.getNombres());
        c.setDocumento(r.getDocumento());

        return repo.save(c);
    }
}