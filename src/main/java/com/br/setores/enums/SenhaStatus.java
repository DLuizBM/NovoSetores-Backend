package com.br.setores.enums;

public enum SenhaStatus {

    AGUARDANDO_ATENDIMENTO("Aguardando Atendimento"),
    EM_ATENDIMENTO("Em Atendimento"),
    ATENDIDA("Atendida");

    private final String status;

    private SenhaStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }


}
