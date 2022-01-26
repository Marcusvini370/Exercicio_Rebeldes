package com.exercicio.rebeldes;

import com.exercicio.rebeldes.validacoes.ValidadorCampo;
import com.exercicio.rebeldes.validacoes.ValidadorCampoNumerico;
import com.exercicio.rebeldes.validacoes.ValidadorCampoString;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.swing.JOptionPane;

public class RebeldeView {

    private IC inteligenciaCentral = new IC();
    private Rebelde rebelde;
    private String nome;
    private Integer idade;
    private RacaEnum raca;

    private void askNome() {
        this.nome = JOptionPane.showInputDialog("Digite o seu nome:");
//        if (nome == null || nome.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Nome inválido!");
//            askNome();
//        } else {
//            this.nome = nome;
//        }

        new ValidadorCampoString(nome, this::askNome).validar();
    }

    private void askIdade() {
        this.idade = Integer.parseInt(JOptionPane.showInputDialog("Qual a idade do Rebelde?"));
//        if (idade <= 0) {
//            JOptionPane.showMessageDialog(null, "Idade inválida!");
//            askIdade();
//        } else {
//            this.idade = idade;
//        }
        new ValidadorCampoNumerico(idade, this::askIdade).validar();

    }

    private void askRaca() {

        RacaEnum[] racas = RacaEnum.values();

        this.raca = (RacaEnum) JOptionPane.showInputDialog(null, "Escolha um", "Race",
                JOptionPane.QUESTION_MESSAGE, null, racas, 3);

//        if (escolhido == null) {
//            JOptionPane.showMessageDialog(null, "Raça Inválida");
//            askRaca();
//        }else {
//            this.raca = escolhido;
//        }
        new ValidadorCampo<RacaEnum>(raca, this::askRaca).validar("Uma das raças deve ser escolhida!");
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

        if (ingressou) {
            JOptionPane.showMessageDialog(null, "Rebelde '" + rebelde.getNome() + "' ingressou na Aliança!");
        } else {
            JOptionPane.showMessageDialog(null, "Rebelde '" + rebelde.getNome() + "' recusado!");
        }
    }

    private void exibirRebeldesIC() {
        StringBuilder reb = new StringBuilder();
        reb.append("Lista de rebeldes:\n");
        for (Rebelde rebelde : this.inteligenciaCentral.getRebeldes()) {
            reb.append(rebelde.toString());
            reb.append("\n");
        }

        JOptionPane.showMessageDialog(null, reb);
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
            MenuEnum menuEscolhido = (MenuEnum) JOptionPane.showInputDialog(null, "Escolha um", "Race",
                    JOptionPane.QUESTION_MESSAGE, null, menu, MenuEnum.SOLICITAR_INGRESSO);
            //            if (menuEscolhido == null) {
            //                JOptionPane.showMessageDialog(null, "Ação inválida");
            //                menuRebelde();
            //            }

            switch (menuEscolhido != null ? menuEscolhido : MenuEnum.SAIR) {
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
            }
        } while (status);

    }

}
