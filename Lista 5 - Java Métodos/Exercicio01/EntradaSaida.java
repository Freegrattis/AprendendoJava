import javax.swing.JOptionPane;

public class EntradaSaida {
    public static double pedirNota(String contador) {
        return Double.parseDouble(JOptionPane.showInputDialog("Informe a " + contador + " nota"));
    }

    public static int tipoMedia(){
        return Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo de media desejada: \n"+
        "[1]-Aritimética\n"+
        "[2]-Ponderada\n"));
    }

    public static int pedirPeso(String contador) {
        return Integer.parseInt(JOptionPane.showInputDialog("Informe o " + contador + " peso"));
    }
}
