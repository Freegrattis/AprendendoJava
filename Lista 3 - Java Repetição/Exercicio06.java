import javax.swing.JOptionPane;

public class Exercicio06 {
    public static void main(String[] args) {
        String exibirTabuada ="";
        int numeroEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Insira um numero para saber sua tabuada: "));
        for (int i=0;i<=10;i++){
            exibirTabuada+= numeroEscolhido+" x "+i+" = "+" "+(numeroEscolhido*i)+"\n";
        }
        JOptionPane.showMessageDialog(null, exibirTabuada);
    }
}
