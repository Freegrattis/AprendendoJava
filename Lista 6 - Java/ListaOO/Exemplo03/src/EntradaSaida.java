import javax.swing.JOptionPane;

public class EntradaSaida {

    public static int solicitarOpcaoMenu() {
        return 0;
    }

    public static String solicitarCaracteristicas(String msg) {
        return JOptionPane.showInputDialog("informe "+msg+" do aluno. ");
    }
    public static int solicitarCaracteristicasNumericas(String msg) {
        return Integer.parseInt(JOptionPane.showInputDialog("informe "+msg+" do aluno. "));
    }

    public static double solicitarNota(String msg) {
        return Double.parseDouble(JOptionPane.showInputDialog("Informe "+msg+" do aluno: "));
    }

    public static String solicitarDescricaoNota(String msg) {
        return JOptionPane.showInputDialog("Informe a descrição da "+msg+" nota");
    }

    public static void mostrarAlunos(String listarAlunos) {
        JOptionPane.showMessageDialog(null, listarAlunos);
    }

    public static void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static int escolherOpcaoMenu() {
        String[] opcoes = {"Cadastrar Alunos e Notas","Mostrar Dados de Todos os Alunos","Mostrar Dados de Aluno Específico","Alterar Nome de Aluno","Adicionar Nova Nota","Mostrar média de aluno","Sair"}; 
        return JOptionPane.showOptionDialog(null, "Escolha a opção: ","MENU",1,1,null,opcoes,null);
    }

    public static void listarNomes(String listarAlunos) {
        JOptionPane.showMessageDialog(null, listarAlunos);
    }

    public static int solicitarPosicao() {
        return Integer.parseInt(JOptionPane.showInputDialog("Digite a posição a ser modificada: "));
    }

    public static int mostrarAviso() {
        String[] opcoes = {"Sim","Não"};
        return JOptionPane.showOptionDialog(null, "Deseja continuar lançando notas? ","MENU",1,1,null,opcoes,null);
    }
    
}
