public class Validacao {
    public static boolean validarSalario(double salarioBruto, boolean validarSalario){
        if(salarioBruto<500||salarioBruto>30000){
            return validarSalario==true;
        }else{
            return validarSalario==false;
        }
        
    }
}
