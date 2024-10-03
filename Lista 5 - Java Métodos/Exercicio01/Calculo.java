public class Calculo {
    public static double mediaPonderada(double num1, double num2, int peso1, int peso2) {
        return (((peso1 * 0.1) * num1) + ((peso2 * 0.1) * num2));
    }
    
    public static double mediaAritimetica(double num1, double num2) {
        return ((num1+num2)/2);
    }
    
}