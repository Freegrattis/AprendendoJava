import javax.swing.JOptionPane;

public class Exercicio02 {
    public static void main(String[] args) {
        double valorTotalCompra= 0;
        double valorUnitarioProduto= Double.parseDouble(JOptionPane.showInputDialog("Informe o valor unitario do produto: "));
        int quantidadeComprada = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade comprada: "));
        while(valorUnitarioProduto<0 || quantidadeComprada<0){
            valorUnitarioProduto= Double.parseDouble(JOptionPane.showInputDialog("Invalido.\nInforme o valor unitario do produto novamente: "));
            quantidadeComprada = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade comprada: "));
        }
        if (quantidadeComprada>12){
            valorUnitarioProduto = valorUnitarioProduto-(valorUnitarioProduto*0.10);
            valorTotalCompra= valorUnitarioProduto *quantidadeComprada;
        }else {
            valorTotalCompra= valorUnitarioProduto *quantidadeComprada;
        }
        JOptionPane.showMessageDialog(null,"O valor tutal da compra é: R$"+valorTotalCompra);
    }

    
}
