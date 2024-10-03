public class Calculo {
    public static double descontarInss(double salarioBruto, double INSS){
        return (salarioBruto*INSS);
    }
    
    public static double descontarIrpf(double salarioBruto, double IRPF){
        return (salarioBruto*IRPF);
    }

    public static double descontarPlanoSaude(double salarioBruto, double PLANOSAUDE){
        return (salarioBruto*PLANOSAUDE);
    }

    public static double calculoHorasExtras(double horasExtras,double valorHora,double salarioBruto){
        valorHora=salarioBruto/160;
        valorHora=valorHora+(valorHora*0.5);
        return horasExtras*valorHora;
    }

    public static double calcularSalarioLiquido(double salarioLiquido,double horasExtras,double salarioBruto,double somaDescontos){
        return (salarioBruto-somaDescontos)+horasExtras;
    }
}
