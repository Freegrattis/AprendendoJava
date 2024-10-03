
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

public class Exercicio06 {
    public static void main(String[] args) {
        int contador = 3;
        String resultado = "";
        int randi = ThreadLocalRandom.current().nextInt(0, 100);
        while(contador != 0 ){
        int numeroEscolhido = Integer
                .parseInt(JOptionPane
                        .showInputDialog("Faça um palpite.\n Você tem " + contador + " chances!( De 0 à 100 )"));
            while ( numeroEscolhido < 0 || numeroEscolhido > 100) {
                numeroEscolhido = Integer
                    .parseInt(JOptionPane
                            .showInputDialog("Faça um palpite novamente.( De 0 à 100 )"));
            }
            if (randi == numeroEscolhido) {
                resultado = " Voce Acertou!";
                break;
            } else {
                resultado = " Voce Errou!";
            }
            contador = contador - 1;
            JOptionPane.showMessageDialog(null, resultado);
        }
        JOptionPane.showMessageDialog(null, resultado + "\n O resultado era: " + randi);
    }
}
