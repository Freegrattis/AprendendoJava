import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

public class Exercicio05 {
    public static void main(String[] args) {
        String exibirVetorInvertido= "";
        String exibirVetor = "";
        int[] numeros = new int[10];
        int auxiliar = 0;
        for (int i = 0; i < 10; i++) {
            numeros[i] = ThreadLocalRandom.current().nextInt(0, 100);
            System.out.println(numeros[i]);
            if (i == 9) {
                exibirVetor+= numeros[i] + ".";
            } else {
                exibirVetor += numeros[i] + ",";
            } 
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                auxiliar = numeros[i];
                numeros[i] = numeros[j];
                numeros[j] = auxiliar;
            }
        } 	
        for (int i = 0; i < 10; i++) {
            if (i == 9) {
                exibirVetorInvertido += numeros[i] + ".";
            } else {
                exibirVetorInvertido += numeros[i] + ",";
            }
        }
        JOptionPane.showMessageDialog(null, "Vetor: "+exibirVetor+"\n"+"Vetor Invertido: "+exibirVetorInvertido); 
    }
}
