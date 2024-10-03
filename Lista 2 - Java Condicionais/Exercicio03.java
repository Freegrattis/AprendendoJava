import javax.swing.JOptionPane;

public class Exercicio03 {
    public static void main(String[] args) {
        String vencedor = "O time vencedor é: ";
       int numeroGolsTimeA = Integer.parseInt(JOptionPane.showInputDialog("Numero de gols do Time A:"));
       int numeroGolsTimeB = Integer.parseInt(JOptionPane.showInputDialog("Numero de gols do Time B:"));
       while (numeroGolsTimeA<0 || numeroGolsTimeB<0) {
        numeroGolsTimeA = Integer.parseInt(JOptionPane.showInputDialog("Invalido.\nNumero de gols do Time A:"));
        numeroGolsTimeB = Integer.parseInt(JOptionPane.showInputDialog("Invalido.\nNumero de gols do Time B:"));
       }  
       if (numeroGolsTimeA==numeroGolsTimeB) {
        vencedor="Empatado.";
       }else if(numeroGolsTimeA<numeroGolsTimeB){
            vencedor+="Time B.";
       }else{
        vencedor+="Time A";
       }
       JOptionPane.showMessageDialog(null, vencedor);
    }
}
