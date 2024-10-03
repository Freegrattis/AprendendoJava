import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

public class Exercicio06 {
    public static void main(String[] args) {
        String exibirVetor1 ="";
        String exibirVetor2 ="";
        String exibirVetor3 ="";
        int[] vet1 = new int[10];
        int[] vet2 = new int[10];
        int[] vet3= new int[10];
        for (int i = 0; i < 10; i++) {
            vet1[i]=ThreadLocalRandom.current().nextInt(0, 100);
             if (i == 9) {
                exibirVetor1+= vet1[i] + ".";
            } else {
                exibirVetor1 += vet1[i] + ",";
            } 
        }
        for (int i = 0; i < 10; i++) {
          vet2[i]=ThreadLocalRandom.current().nextInt(0, 100);
            if (i == 9) {
                exibirVetor2+= vet2[i] + ".";
            } else {
                exibirVetor2 += vet2[i] + ",";
            } 
        }
        for (int i = 0; i < 10; i++) {
            vet3[i]=vet1[i]+vet2[i];
            if (i == 9) {
                exibirVetor3+= vet3[i] + ".";
            } else {
                exibirVetor3 += vet3[i] + ",";
            } 
        }
        JOptionPane.showMessageDialog(null, "Vetor 1: "+exibirVetor1+"\nVetor 2: "+exibirVetor2+"\nSoma dos vetores é: "+exibirVetor3);   
    }   
}