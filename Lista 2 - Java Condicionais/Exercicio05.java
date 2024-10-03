import javax.swing.JOptionPane;

public class Exercicio05 {
    public static void main(String[] args) {
        double quantidadeMediaEstoque = 0;
        int quantidadeAtual = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade atual no estoque."));
        int quantidadeMaxima = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade maxima no estoque."));
        int quantidadeMinima = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade minima no estoque."));
        while (quantidadeMaxima<0 || quantidadeMinima<0) {
            quantidadeMaxima = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade maxima no estoque."));
            quantidadeMinima = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade minima no estoque."));
        }
        quantidadeMediaEstoque =  (quantidadeMaxima+quantidadeMinima)/2;
        if (quantidadeAtual >= quantidadeMediaEstoque){
            JOptionPane.showMessageDialog(null,"Não efeuar compra.");
        }else{
            JOptionPane.showMessageDialog(null,"Efeuar compra.");
        }
    }
}
