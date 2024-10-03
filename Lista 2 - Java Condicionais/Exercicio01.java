import javax.swing.JOptionPane;

public class Exercicio01 {
    public static void main(String[] args){
         int idade = Integer.parseInt(JOptionPane.showInputDialog("Informe sua idade: "));
        String classificacao="Classificado como: ";
        while (idade < 0){
            idade = Integer.parseInt(JOptionPane.showInputDialog("Invalido.\nInforme sua idade novamente: "));
        }
        if (idade <= 14){
            classificacao += "Criança";
        } else if(idade>=15 && idade<=17){
            classificacao += "Adolecente";
        } else if(idade>=18 && idade<=30){
            classificacao += "Adulto jovem";
        }else{
            classificacao += "Adulto";
        }
        JOptionPane.showMessageDialog(null, idade+" anos.\n"+classificacao);
    }
    
}