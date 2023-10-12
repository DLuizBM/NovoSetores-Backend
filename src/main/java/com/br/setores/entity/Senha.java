package com.br.setores.entity;

import com.br.setores.enums.SenhaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_senha")
public class Senha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long numeroSenha;

    @ManyToOne
    private Fila fila;

    @ManyToOne
    private Guiche guiche;

    @Column(name = "status_senha")
    @Enumerated(EnumType.STRING)
    private SenhaStatus senhaStatus;

    private Date dataEmissao;

}
