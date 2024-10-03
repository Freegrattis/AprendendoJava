import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Exercicio02 {

    public static void main(String[] args) {

        DecimalFormat arrendondarResultado = new DecimalFormat("#,###.00");

        double numeroUm = Double.parseDouble(JOptionPane.showInputDialog("Insira o 1° numero"));
        double numeroDois = Double.parseDouble(JOptionPane.showInputDialog("Insira o 2° numero"));

        String restoDivisao = "";
        String divisao = "";

        double soma = (numeroUm + numeroDois);
        double subtracao = (numeroUm - numeroDois);
        double multiplicacao = (numeroUm * numeroDois);
        double exponencial = Math.pow(numeroUm, numeroDois);
        if (numeroDois != 0) {
            restoDivisao = "" + arrendondarResultado.format(numeroUm % numeroDois);
            divisao = "" + arrendondarResultado.format(numeroUm / numeroDois);
        } else {
            divisao = "A divisao não pode ser realizada.";
            restoDivisao = "A divisao não pode ser realizada.";

        }
        JOptionPane.showMessageDialog(null,
                "Soma: " + soma + "\n" + "Subtração: " + subtracao + "\n" + "Multiplicação: " + multiplicacao + "\n"
                        + "Exponencial: " + exponencial + "\n" + "Divisao: " + divisao
                        + "\n" + "RestoDivisao: " + restoDivisao + "\n");

    }
}