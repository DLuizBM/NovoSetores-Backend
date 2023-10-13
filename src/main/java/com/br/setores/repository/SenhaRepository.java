package com.br.setores.repository;

import com.br.setores.entity.Senha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SenhaRepository extends JpaRepository<Senha, Long> {

    @Query(value = SQLExpression.SELECT_FIRST_EMITTED_DATA, nativeQuery = true)
    Senha findByFirstDataEmissao(Long idFila);

    @Query(value = SQLExpression.SELECT_LAST_CALLED, nativeQuery = true)
    Senha findByLastCalled(Long idFila);
}
