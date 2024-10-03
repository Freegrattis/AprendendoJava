import javax.swing.JOptionPane;

public class Exercicio04 {
    public static void main(String[] args){
        double valorBase = 3000.00;
        double valorTotal = 0;
        String incluirComida = "Comida inclusa: ";
        String destinoViagem="O destido de viagem é: ";
        int opcaoViagem = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção de viagem: \n[1]-Maceió \n [2]-Porto de Galinhas"));
        while(opcaoViagem<1 || opcaoViagem>2){
            opcaoViagem = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção de viagem: \n[1]-Maceió \n [2]-Porto de Galinhas"));
        }
        switch (opcaoViagem) {
            case 1:
                destinoViagem+="Maceió";
                opcaoViagem = Integer.parseInt(JOptionPane.showInputDialog("Deseja incluir o almoço e janta no pacote? \n[1]-Sim \n [2]-Não"));
                while (opcaoViagem<1 || opcaoViagem>2) {
                    opcaoViagem = Integer.parseInt(JOptionPane.showInputDialog("Deseja incluir o almoço e janta no pacote? \n[1]-Sim \n [2]-Não"));   
                }
                switch (opcaoViagem) {
                    case 1:
                        valorTotal = valorBase + valorBase;
                        break;
                    case 2:
                        valorTotal = valorBase + (valorBase*0.85);
                        break;
                }
                break;
            case 2:
                destinoViagem+="Porto de Galinhas";
                opcaoViagem = Integer.parseInt(JOptionPane.showInputDialog("Deseja incluir o almoço e janta no pacote? \n[1]-Sim \n [2]-Não"));
                while (opcaoViagem<1 || opcaoViagem>2) {
                    opcaoViagem = Integer.parseInt(JOptionPane.showInputDialog("Deseja incluir o almoço e janta no pacote? \n[1]-Sim \n [2]-Não"));   
                }
                switch (opcaoViagem) {
                    case 1:
                        valorTotal = valorBase + (valorBase*0.60);
                        break;
                    case 2:
                        valorTotal = valorBase + (valorBase*0.45);
                        break;
                }
                break;            
        }
        JOptionPane.showMessageDialog(null, destinoViagem+"\n"+incluirComida+"\n O valor total é: R$"+valorTotal);
        
    }
    
}
