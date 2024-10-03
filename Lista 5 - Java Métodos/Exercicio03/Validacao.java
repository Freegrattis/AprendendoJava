public class Validacao{
    public static boolean validaValorEmprestimo(double valorEmprestimo,boolean valorEmprestimoAdequado) {
        if(valorEmprestimo>200000){
            EntradaSaida.emprestimoInvalido();
           return valorEmprestimoAdequado=false;
        }else{
            return valorEmprestimoAdequado=true;
        }
        

    }
    public static boolean validarValorParcela(double parcela,double salario, boolean valorParcelaAdequado) {
        if(parcela<(salario*015)){
            return valorParcelaAdequado = true;
        }
        return valorParcelaAdequado=false;
    }
}
