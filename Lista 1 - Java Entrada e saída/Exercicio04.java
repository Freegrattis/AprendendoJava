import javax.swing.JOptionPane;

public class Exercicio04 {

    public static void main(String[] args){

        int numeroEscolhido = Integer.parseInt(JOptionPane.showInputDialog("Insira um numero: "));

        int antecessor = numeroEscolhido - 1;
        int sucessor = numeroEscolhido + 1;

        JOptionPane.showMessageDialog(null, "Numero escolhido: "+numeroEscolhido+"\n"+"Antecessor: "+ antecessor+ "\n"+"Sucessor: "+ sucessor);


    }
    
}
