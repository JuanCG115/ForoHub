package com.alurachallenge.ForoHub.domain;

import com.alurachallenge.ForoHub.dto.DatosActualizarTopico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public void actualizarDatos(DatosActualizarTopico datos, Curso curso) {
        if (datos.titulo() != null) this.titulo = datos.titulo();
        if (datos.mensaje() != null) this.mensaje = datos.mensaje();
        if (curso != null) this.curso = curso;
    }
}
