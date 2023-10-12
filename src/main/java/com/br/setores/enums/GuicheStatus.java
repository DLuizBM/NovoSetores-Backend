package com.br.setores.enums;

public enum GuicheStatus {

    UTILIZADO("Utilizado"),
    NAO_UTILIZADO("Não Utilizado");

    private final String status;

    private GuicheStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
