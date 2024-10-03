public class Principal{
    public static void main(String[] args) {
        boolean salarioInvalido = true;
        double somaDescontos=0;
        double salarioBruto = 0;
        double valorHora=0;
        double salarioLiquido=0;
        double INSS=0.2;
        double IRPF=0.1;
        double PLANOSAUDE=0.05;
        double horasExtras = 0;
        while(salarioInvalido==true){
            salarioBruto=EntradaSaida.pedirSalario(salarioBruto);
            salarioInvalido=Validacao.validarSalario(salarioBruto, salarioInvalido);
        }       
        horasExtras=EntradaSaida.pedirHorasExtras(horasExtras);
        horasExtras=Calculo.calculoHorasExtras(horasExtras, valorHora,salarioBruto);
        somaDescontos=Calculo.descontarInss(salarioBruto, INSS);
        somaDescontos+=Calculo.descontarIrpf(salarioBruto, IRPF);
        somaDescontos+=Calculo.descontarPlanoSaude(salarioBruto, PLANOSAUDE);        
        salarioLiquido=Calculo.calcularSalarioLiquido(salarioLiquido,horasExtras,salarioBruto,somaDescontos);
        EntradaSaida.mostrarSalario(salarioLiquido);
    }
}