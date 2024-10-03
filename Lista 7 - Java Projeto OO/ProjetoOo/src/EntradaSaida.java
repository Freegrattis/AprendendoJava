import javax.swing.JOptionPane;

public class EntradaSaida {
    public static int escolherOpcaoMenu(){
        String[] opcoes = {"Cadastrar","Ver todas","Editar","Excluir","Adicionar","Retirar","Consultar marca","Consultar código","Produtos a comprar","Comprar produtos","Histórico de compras","Sair"};
        return (JOptionPane.showOptionDialog(null, "Escolha a opção: ", "MENU", 1, 1, null, opcoes, null));
    }

    public static String receberCaracteristica(String msg) {
        return JOptionPane.showInputDialog("Insira "+msg+" da ferramenta: ");
    }

    public static double receberCaracteristicaDouble(String msg) {
        return Double.parseDouble(JOptionPane.showInputDialog("Digite "+msg+" da ferramenta:"));
    }

    public static void mostrarTodasFerramentas(String listarFerramentas){
        JOptionPane.showMessageDialog(null, listarFerramentas);
    }

    public static void mostrarAlerta(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static int receberCaracteristicaInteiro(String msg) {
        return Integer.parseInt(JOptionPane.showInputDialog("Digite "+msg+" de ferramentas: "));
    }

    public static int perguntarExclusaoFerramenta(String msg) {
        String[] opcoes = {"Confirmar","Voltar"};
        return JOptionPane.showOptionDialog(null, msg, "MENU", 1, 1, null, opcoes, null); 
    }

    public static void mostrarFerramentasMarca(String listarFerramentasMarca) {
        JOptionPane.showMessageDialog(null, listarFerramentasMarca);
    }

    public static void mostrarFerramentasCodigo(Object listarFerramentasCodigo) {
        JOptionPane.showMessageDialog(null, listarFerramentasCodigo);
    }

    public static void mostrarAComprar(Object listarFerramentasComprar) {
        JOptionPane.showMessageDialog(null, listarFerramentasComprar);
    }

    public static long receberCpf() {
        return Long.parseLong(JOptionPane.showInputDialog("Informe seu CPF:"));
    }

    public static void mostrarEmEstoque(Object listarFerramentasEmEstoque) {
        JOptionPane.showMessageDialog(null, listarFerramentasEmEstoque);
    }

    public static String receberCaracteristicaCliente(String msg) {
        return JOptionPane.showInputDialog("Digite "+msg+": ");
    }

    public static boolean escolherOpcaoVenda() {
        int opcao=0;
        String[] opcoes = {"Selecionar mais um produto","Finalizar"};
        opcao= JOptionPane.showOptionDialog(null, "Escolha uma opção", "MENU", 1, 1, null, opcoes, null); 
        if(opcao==0){
            return true;
        }else{
            return false;
        }
    }
    public static boolean realizarCompra() {
        int opcao=0;
        String[] opcoes = {"Comprar","Voltar"};
        opcao= JOptionPane.showOptionDialog(null, "Escolha uma opção", "MENU", 1, 1, null, opcoes, null);
        if(opcao==0){
            return true;
        }else{
            return false;
        }
    }
    
    public static void mostrarTodasVendas(String listarVendas){
        JOptionPane.showMessageDialog(null, listarVendas);
    }
}
    
