package com.br.setores.entity;

import com.br.setores.enums.GuicheStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_guiche")
public class Guiche implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "status_guiche")
    @Enumerated(EnumType.STRING)
    private GuicheStatus guicheStatus;
}
