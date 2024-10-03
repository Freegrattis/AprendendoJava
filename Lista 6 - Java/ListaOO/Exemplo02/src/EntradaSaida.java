import javax.swing.JOptionPane;

public class EntradaSaida {
    public static int escolherOpcaoMenu(){
        String[] opcoes = {"Cadastrar peças","Remover uma peça","Visualizar todas","Sair"};
        return (JOptionPane.showOptionDialog(null, "Escolha a opção: ", "MENU", 1, 1, null, opcoes, null));
    }

    public static String recebeCaracteristica(String msg) {
        return JOptionPane.showInputDialog("Insira "+msg+" da peça: ");
    }

    public static double recebeCaracteristicaNumerica(String msg) {
        return Double.parseDouble(JOptionPane.showInputDialog("Digite "+msg+" da peça:"));
    }

    public static void mostrarPecas(String listarPecas){
        JOptionPane.showMessageDialog(null, listarPecas);
    }

    public static void mostrarAlerta(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static int recebeQuantidade(String msg) {
        return Integer.parseInt(JOptionPane.showInputDialog("Digite "+msg+" de peças: "));
    }

    public static int solicitaPosicao() {
        return Integer.parseInt(JOptionPane.showInputDialog("Digite a posição da peça: "));
    }
}
