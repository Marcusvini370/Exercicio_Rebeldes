package com.exercicio.rebeldes;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class RebeldeView {

    private IC inteligenciaCentral = new IC();
    private Rebelde rebelde;
    private String nome;
    private Integer idade;
    private RacaEnum raca;


    private void askNome() {
        String nome = JOptionPane.showInputDialog("Digite o seu nome:");
        if (nome == null || nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome inválido!");
            askNome();
        } else
            this.nome = nome;
    }

    private void askIdade() {
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Qual a idade do Rebelde?"));
        if ( idade <= 0) {
            JOptionPane.showMessageDialog(null, "Idade inválida!");
            askIdade();
        } else {
            this.idade = idade;}
    }

    private void askRaca() {

        RacaEnum[] raca = RacaEnum.values();

        RacaEnum escolhido =  (RacaEnum) JOptionPane.showInputDialog(null, "Escolha um", "Race",
                JOptionPane.QUESTION_MESSAGE, null, raca, 3);


        if (escolhido == null) {
            JOptionPane.showMessageDialog(null, "Raça Inválida");
            askRaca();
        }else {
            this.raca = escolhido;
        }
    }

    private void obtemDadosRebelde() {
        askNome();
        askIdade();
        askRaca();

        this.rebelde = Rebelde.builder()
                .nome(this.nome)
                .idade(this.idade)
                .raca(this.raca)
                .build();
    }

    private void solicitaIngressoIC() {
        boolean ingressou = this.inteligenciaCentral.solicitarIngressoDeRebelde(this.rebelde);

        if(ingressou) {
            JOptionPane.showMessageDialog(null, "Rebelde '" + rebelde.getNome() + "' ingressou na Aliança!");
        } else {
            JOptionPane.showMessageDialog(null, "Rebelde '" + rebelde.getNome() + "' recusado!");
        }
    }

    private void exibirRebeldesIC() {
        StringBuilder reb = new StringBuilder();
        reb.append("Lista de rebeldes:\n");
        for (Rebelde rebelde : this.inteligenciaCentral.getRebeldes())
        {
            reb.append(rebelde.toString());
            reb.append("\n");
        }

        JOptionPane.showMessageDialog(null ,reb);
    }

    private void gerarRelatorioRebeldesIC() {
        try {
            this.inteligenciaCentral.gerarRelatorioDeRebeldes();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void menuRebelde() {

        MenuEnum[] menu = MenuEnum.values();




        boolean status = true;
        do {
            MenuEnum menuEscolhido =  (MenuEnum) JOptionPane.showInputDialog(null, "Escolha um", "Race",
                    JOptionPane.QUESTION_MESSAGE, null, menu, MenuEnum.SOLICITAR_INGRESSO);

            if (menuEscolhido == null){
                JOptionPane.showMessageDialog(null, "Ação inválida");
                menuRebelde();
            }

            switch (Objects.requireNonNull(menuEscolhido)) {
                case SOLICITAR_INGRESSO:
                    obtemDadosRebelde();
                    solicitaIngressoIC();

                    break;
                case EXIBIR_LISTA:
                    exibirRebeldesIC();

                    break;
                case GERAR_RELATORIO:
                    gerarRelatorioRebeldesIC();

                    break;
                case SAIR:
                    status = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ação inválida");
                    menuRebelde();
            }
        } while (status);

    }

}
