package com.alurachallenge.ForoHub.controller;

import com.alurachallenge.ForoHub.dto.DatosActualizarTopico;
import com.alurachallenge.ForoHub.dto.DatosListadoTopico;
import com.alurachallenge.ForoHub.dto.DatosRegistroTopico;
import com.alurachallenge.ForoHub.repository.TopicoRepository;
import com.alurachallenge.ForoHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;
    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        var response = service.registrar(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio) {

        if (curso != null && anio != null) {
            return ResponseEntity.ok(repository.findByCursoNombreAndAnio(curso, anio, paginacion).map(DatosListadoTopico::new));
        }
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        var topico = repository.getReferenceById(datos.id());
        // Validar duplicados si cambia título/mensaje...
        topico.actualizarDatos(datos, null);
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
