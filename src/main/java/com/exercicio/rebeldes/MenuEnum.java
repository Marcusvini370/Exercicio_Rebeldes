package com.exercicio.rebeldes;

import lombok.Getter;

@Getter
public enum MenuEnum {

    SOLICITAR_INGRESSO(" Solicitar Ingresso na Aliança") ,
    EXIBIR_LISTA("Exibir Lista de Rebeldes") ,
    GERAR_RELATORIO("Gerar Relatório de Rebeldes") ,
    SAIR("Sair");

    private String menu;

    MenuEnum(String string) {
        this.menu = string;
    }
}
