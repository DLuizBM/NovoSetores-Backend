package com.br.setores.repository;

import com.br.setores.entity.Fila;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilaRepository extends JpaRepository<Fila, Long> {

    List<Fila> findByFilaAtiva(Boolean filaAtiva);

}
