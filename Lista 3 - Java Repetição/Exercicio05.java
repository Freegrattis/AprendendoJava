import javax.swing.JOptionPane;

public class Exercicio05 {
     public static void main(String[] args) {
          int negativo = 0;
          int positivo = 0;
          String risco = "";
          int contador = 1;
          String continuar = "";
          while (continuar.equals("n") != true) {
               int saldo = Integer.parseInt(JOptionPane.showInputDialog(contador + "° pessoa.\n Informe seu saldo:"));
               if (saldo >= 0) {
                    positivo = positivo + 1;
               } else {
                    negativo = negativo + 1;
               }
               contador = contador + 1;
               continuar = JOptionPane.showInputDialog("Deseja continuar avaliando? ('S' para sim ou 'N' para não) ");
               continuar = continuar.toLowerCase();
          }
          double calcularRisco = (contador - 1) / 2;
          if (contador - 1 == 1 && positivo == 1) {
               risco = "Nenhum risco!";
          } else if (contador - 1 == 1 && negativo == 1) {
               risco = "Risco ao banco!";
          } else if (positivo >= calcularRisco) {
               risco = "Nenhum risco!";
          } else {
               risco = "Risco ao banco!";
          }
          JOptionPane.showMessageDialog(null, risco);
     }
}