import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Exercicio03 {

    public static void main(String[] args) {

        DecimalFormat arrendondarResultado = new DecimalFormat("#,###.00");

        final double IMPOSTO = 20;
        final double REPRESENTANTE = 30;

        double valorCompra = Double.parseDouble(JOptionPane.showInputDialog("Insira o valor da compra: "));
        double valorVenda = 0;

        double valorTotalImposto = (valorCompra * IMPOSTO / 100);
        double valorTotalRepresentante = (valorCompra * REPRESENTANTE / 100);

        valorVenda = (valorCompra + (valorCompra * IMPOSTO / 100) + (valorCompra * REPRESENTANTE / 100));

        JOptionPane.showMessageDialog(null, arrendondarResultado.format(valorVenda) + "\n" + "Imposto: " + (valorTotalImposto) + "\n" + "Representante: " + (valorTotalRepresentante));

    }

}
