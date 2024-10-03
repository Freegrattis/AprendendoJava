import javax.swing.JOptionPane;

public class Exercicio02 {
    public static void main(String[] args) {
        int[] numeros = new int[5];
        int contador = 5;
        int posicaoMaior = 0;
        int posicaoMenor = 0;
        int menor=0;
        int maior=0;
        for (int i = 0; i < 5; i++) {
            numeros[i] = Integer.parseInt(JOptionPane.showInputDialog("Informe cinco numeros inteiro.\nFaltam: "+contador));
            if (numeros[i]<menor){
                menor = numeros[i];
                posicaoMenor = i;
            }else if(numeros[i]>maior){
                maior = numeros[i];
                posicaoMaior = i;
            }
            contador = contador-1;
        }
        JOptionPane.showMessageDialog(null, "Maior numero: "+maior+". Posição: "+posicaoMaior+" do vetor.\nMenor Numero: "+menor+". Posição: "+posicaoMenor+" do vetor.");
    }
}

