import javax.swing.JOptionPane;

public class EntradaSaida {
    public static double pedirSalario(double salarioBruto){
        return Double.parseDouble(JOptionPane.showInputDialog("Informe seu salario bruto: "));
    }

    public static double pedirHorasExtras(double horasExtras){
        return Double.parseDouble(JOptionPane.showInputDialog("Informe a quantidade de horas extras feitas: "));
    }
    
    public static void mostrarSalario(double salarioLiquido){
        JOptionPane.showMessageDialog(null, "Salario Liquido: R$"+salarioLiquido);
    }
}
