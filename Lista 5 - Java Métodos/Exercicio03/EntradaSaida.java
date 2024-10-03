import javax.swing.JOptionPane;

public class EntradaSaida {
    public static double pedirSalario() {
        return Double.parseDouble(JOptionPane.showInputDialog("Informe seu salario: "));
    }
    public static double solicitarValorEmprestimo() {
        return Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do emprestimo: "));
    }
    public static void emprestimoInvalido() {
        JOptionPane.showMessageDialog(null, "Não e possivel fazer o emprestimo");
    }
    public static void emprestimoValido(double parcela,double emprestimo) {
        JOptionPane.showMessageDialog(null, "Emprestimo pode ser realizado!\nValor Parcela: R$"+parcela+"\nValor total a ser pago: R$"+emprestimo);

    }

}
