import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Exercicio06 {

    public static void main(String[] args) {

        DecimalFormat arrendondarResultado = new DecimalFormat("#,###.00");

        double primeiraNota = Double.parseDouble(JOptionPane.showInputDialog("Qual a 1° nota? "));
        double segundaNota = Double.parseDouble(JOptionPane.showInputDialog("Qual a 2° nota? "));
        

        double mediaPonderada = (0.4*primeiraNota)+(0.6*segundaNota);

        JOptionPane.showMessageDialog(null, "Sua media ponderada é: " + arrendondarResultado.format(mediaPonderada));
    
    }
}
