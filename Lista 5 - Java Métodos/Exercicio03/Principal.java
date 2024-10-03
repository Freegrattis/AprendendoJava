public class Principal{
    public static void main(String[] args) {
        boolean valorEmprestimoAdequado=false;
        boolean valorParcelaAdequado=false;
        double JUROS=0.35;
        double salario = 0;
        double valorEmprestimo=0;
        salario=EntradaSaida.pedirSalario();
        valorEmprestimo= EntradaSaida.solicitarValorEmprestimo();
        valorEmprestimoAdequado=Validacao.validaValorEmprestimo(valorEmprestimo,valorEmprestimoAdequado);
        valorEmprestimo=Calculo.calcularEmprestimo(salario, valorEmprestimo, JUROS);
        double parcela=Calculo.calcularParcelas(valorEmprestimo);
        valorParcelaAdequado=Validacao.validarValorParcela(parcela,salario, valorParcelaAdequado);
        if (valorEmprestimoAdequado==true && valorParcelaAdequado==true){
            EntradaSaida.emprestimoValido(parcela,valorEmprestimo);
        }else{
            EntradaSaida.emprestimoInvalido();
        }
    }
}