import javax.swing.JOptionPane;

public class EntradaSaida{
    public static String receberMarca(){
        return JOptionPane.showInputDialog("Digite a marca do produto: ");
    }
    public static double receberCaracteristicaNumerica(String msg){
        return Double.parseDouble(JOptionPane.showInputDialog("Digite "+msg+" do produto: "));
    }
    public static String receberCaracteristica(String msg){
        return JOptionPane.showInputDialog("Digite "+msg+" do produto: ");
    }
    public static int escolherOpcaoMenu(){
        String[] opcoes = {"Inserir produtos","Visualizar produtos","Sair"};
        return (JOptionPane.showOptionDialog(null, "Escolha a opção: ", "MENU", 1, 1, null, opcoes, null));
    }
    public static void mostrarProdutos(String listarProdutos) {
        JOptionPane.showMessageDialog(null, listarProdutos);
    }
    public static void mostrarAlerta(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}