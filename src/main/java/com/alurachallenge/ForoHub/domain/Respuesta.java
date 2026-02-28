package com.alurachallenge.ForoHub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private Boolean solucion = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
}
