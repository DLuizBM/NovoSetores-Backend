package com.br.setores.repository;

public interface SQLExpression {

    public static String SELECT_FIRST_EMITTED_DATA = "SELECT * FROM tb_senha as senha " +
            "WHERE senha.fila_id = :idFila " +
            "AND senha.status_senha = 'AGUARDANDO_ATENDIMENTO' " +
            "AND senha.data_emissao = (SELECT min(tb_senha.data_emissao) FROM tb_senha  " +
                                        "WHERE  tb_senha.status_senha = 'AGUARDANDO_ATENDIMENTO')";
}
