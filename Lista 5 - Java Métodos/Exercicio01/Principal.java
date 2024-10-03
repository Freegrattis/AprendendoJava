public class Principal{
    public static void main(String[] args) {
        boolean notaInvalida=true;
        double media=0;
        double num2=0;
        double num1=0;
        while(notaInvalida==true){
           num1=EntradaSaida.pedirNota("1ª");
           notaInvalida=Validacao.validaNota1("1ª", num1, notaInvalida);
        }
        notaInvalida=Validacao.resetarVariavel(notaInvalida);
        while(notaInvalida==true){
           num2=EntradaSaida.pedirNota("2ª");
           notaInvalida=Validacao.validaNota2("2ª", num2, notaInvalida);
        }
        int opcao=EntradaSaida.tipoMedia();
        switch (opcao) {
            case 1:
                media=Calculo.mediaAritimetica(num1, num2);
                break;
            case 2:
                int peso1 =EntradaSaida.pedirPeso("1º");
                int peso2 =EntradaSaida.pedirPeso("2º");
                media=Calculo.mediaPonderada(num1, num2, peso1, peso2);
                break;
            default:
                break;
        }
        Resultado.mostrarResultado(media);
    }
}
