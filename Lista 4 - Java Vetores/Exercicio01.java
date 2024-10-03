import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

public class Exercicio01 {
    public static void main(String[] args) {
        int[] numeros = new int[10];
        int posicao = 0;
        String resultado = "Não encontrado!";
        for (int i = 0; i < 10; i++) {
            numeros[i] = ThreadLocalRandom.current().nextInt(0, 100);
            System.out.println(numeros[i]);
        }
        int numeroEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Informe um numero. (De 0 à 100)"));
        while (numeroEscolhido < 0 || numeroEscolhido > 100) {
            numeroEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Informe novamente.( De 0 à 100 )"));
        }
        for (int i = 0; i < 10; i++) {
            if (numeros[i] == numeroEscolhido) {
                posicao = i+1;
                resultado = "Encontrado!\n"+" Posição: "+posicao;
            }
        }
        JOptionPane.showMessageDialog(null, resultado);
    }
}