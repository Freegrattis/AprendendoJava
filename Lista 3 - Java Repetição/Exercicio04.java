import javax.swing.JOptionPane;

public class Exercicio04 {
    public static void main(String[] args){
        //declarar fora do escopo
        String exibirValor = "";
        int numeroEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Escolha um numero: "));
        int incremento = Integer.parseInt(JOptionPane.showInputDialog("Escolha um incremento: "));
        for(int i=0; i<=numeroEscolhido; i+=incremento){
            //JOptionPane.showMessageDialog(null,i);
            exibirValor += i+" ";
        }   
        JOptionPane.showMessageDialog(null, exibirValor);
    }
}
