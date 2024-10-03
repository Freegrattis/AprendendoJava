public class Calculo {
    public static double calcularParcelas(double emprestimo) {
        return emprestimo/24;
    }
    
    public static double calcularEmprestimo(double salario,double emprestimo,double JUROS) {
        
        return emprestimo=emprestimo+(emprestimo*JUROS);
    }
}
