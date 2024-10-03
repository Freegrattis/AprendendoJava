import javax.swing.JOptionPane;

public class Exercicio02 {
    public static void main(String[] args) {
        int acimaIdade = 0;
        int acimaAltura = 0;
        int abaixoPeso = 0;
        JOptionPane.showMessageDialog(null,"Cadastre a idade, altura e peso de 10 pessoas.");
        for (int i = 0; i <10; i++) {
            int idade = Integer.parseInt(JOptionPane.showInputDialog((i+1)+"° Pessoa.\nInsira sua idade: "));
            double altura = Double.parseDouble(JOptionPane.showInputDialog((i+1)+"° Pessoa.\nInsira sua altura: "));
            double peso = Double.parseDouble(JOptionPane.showInputDialog((i+1)+"° Pessoa.\nInsira seu peso: "));
            while(idade<=0 || altura <=0 || peso <=0){
                JOptionPane.showMessageDialog(null,"Insira informações válidas.");
                idade = Integer.parseInt(JOptionPane.showInputDialog((i+1)+"° Pessoa.\nInsira sua idade: "));
                altura = Double.parseDouble(JOptionPane.showInputDialog((i+1)+"° Pessoa.\nInsira sua altura: "));
                peso = Double.parseDouble(JOptionPane.showInputDialog((i+1)+"° Pessoa.\nInsira seu peso: "));
            } 
            
            
            if (idade >50) {
                acimaIdade = acimaIdade + 1;
            }
            if(altura > 1.60){
                acimaAltura = acimaAltura +1;
            }
            if(peso < 80){
                abaixoPeso = abaixoPeso +1;
            }           
        }
         JOptionPane.showMessageDialog(null, "Quantidade de pessoas com idade acima de 50 anos: " + acimaIdade+"\n"+
         "Quantidade de pessoas com altura acima de 1.60 m: " + acimaAltura+"\n"+
         "Quantidade de pessoas com peso abaixo de 80 kg: " + acimaAltura+"\n");
    }
}

