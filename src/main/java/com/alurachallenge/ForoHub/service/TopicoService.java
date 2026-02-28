package com.alurachallenge.ForoHub.service;

import com.alurachallenge.ForoHub.domain.StatusTopico;
import com.alurachallenge.ForoHub.domain.Topico;
import com.alurachallenge.ForoHub.dto.DatosListadoTopico;
import com.alurachallenge.ForoHub.dto.DatosRegistroTopico;
import com.alurachallenge.ForoHub.repository.CursoRepository;
import com.alurachallenge.ForoHub.repository.TopicoRepository;
import com.alurachallenge.ForoHub.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public DatosListadoTopico registrar(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidationException("Este tópico ya existe.");
        }
        var autor = usuarioRepository.findById(datos.idAutor()).orElseThrow();
        var curso = cursoRepository.findById(datos.idCurso()).orElseThrow();

        var topico = new Topico(null, datos.titulo(), datos.mensaje(), LocalDateTime.now(), StatusTopico.NO_RESPONDIDO, autor, curso);
        topicoRepository.save(topico);
        return new DatosListadoTopico(topico);
    }
}
