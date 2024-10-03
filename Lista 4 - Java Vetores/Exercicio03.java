import javax.swing.JOptionPane;
public class Exercicio03 {
    public static void main(String[] args) {
        double[] numeros = new double[10];
        double soma = 0;
        double media = 0;
        String resultado = "Os valores são:\n";
        for (int i = 0; i < 10; i++) {
            numeros[i] = Double.parseDouble(JOptionPane.showInputDialog("Informe 10 numeros reais.\n" + (i+1)+"° numero."));
            if (i == 9) {
                resultado += numeros[i] + ".";
            } else {
                resultado += numeros[i] + ",";
            }
            soma = soma + numeros[i];
            
        }
//transferi o cálculo para cá para ser feito somente uma vez
        media = soma/10;
        JOptionPane.showMessageDialog(null, resultado+"\nSoma: "+soma+"\nMedia: "+media);
    }
}