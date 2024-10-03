import javax.swing.JOptionPane;

public class EntradaSaida {

    public static int escolherOpçãoMenu() {
        String[] opcoes = {"Cadastrar tarefas","Visualizar tarefas","Visualizar em aberto","Visualizar concluidas","Alterar status","Excluir uma tarefa","Ver todos os tipos","Sair"};
        return JOptionPane.showOptionDialog(null,"Opções:", "MENU",1,1,null,opcoes,null);
    }

    public static String receberInformacoesTarefa(String msg) {
        return JOptionPane.showInputDialog("Digite "+msg+" da tarefa: ");
    }

    public static void mostrarTarefas(String mostrarTarefas) {
        JOptionPane.showMessageDialog(null, mostrarTarefas, "TAREFAS", 0);
    }

    public static int solicitarPosicao(String msg) {
        return Integer.parseInt(JOptionPane.showInputDialog("Digite a posição da tarefa a ser "+msg+": "));
    }

    public static String mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg, "AVISO", 0);
        return null;
    }


}
