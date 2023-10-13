package com.br.setores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_fila")
public class Fila implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private Long senhaMinima;

    private Long senhaMaxima;

    private String textoInformativo;

    private Long contadorSenhas;

    private Boolean filaAtiva;

    private String prefixo;

}
