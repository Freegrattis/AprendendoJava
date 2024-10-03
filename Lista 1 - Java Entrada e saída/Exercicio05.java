import javax.swing.JOptionPane;

public class Exercicio05 {

    public static void main(String[] args) {

        int horas = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de *horas*? "));
        int minutos = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de *minutos*? "));
        int segundos = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de *segundos*? "));

        int totalSegundos = (horas * 3600) + (minutos * 60) + segundos;

        JOptionPane.showMessageDialog(null, "A quantidade total é: " + totalSegundos + " segundos");

    }

}
