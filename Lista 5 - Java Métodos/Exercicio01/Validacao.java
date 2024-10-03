public class Validacao {
    public static boolean validaNota1(String contador,double num1,boolean notaInvalida) {
        if((num1>10||num1<0)){
            return notaInvalida==true;
        }else{        
         return notaInvalida==false;
        }
    } 

    public static boolean validaNota2(String contador,double num2,boolean notaInvalida) {
        notaInvalida=true;
        if((num2>10||num2<0)){
            return notaInvalida==true;
        }else{        
         return notaInvalida==false;
        }
    }

    public static boolean resetarVariavel(boolean notaInvalida) {
        return notaInvalida==false;
    } 
}
