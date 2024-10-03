import javax.swing.JOptionPane;

public class Exercicio04 {
    public static void main(String[] args) {
        String resposta = "";
        boolean retornarDisciplinaInvalida;
        int i = 0;
        String pesquisarDisciplina = "";
        String continuarPesquisando ="";
        String[] disciplina = new String[4];
        String[] cargaHoraria = new String[4];
        String[] professor = new String[4];
        for (i = 0; i < 4; i++) {
            disciplina[i] = JOptionPane.showInputDialog("Nome da disciplina:");
            cargaHoraria[i] = JOptionPane.showInputDialog("Carga Horária da disciplina:");
            professor[i] = JOptionPane.showInputDialog("Nome do Professor:");
        }
        continuarPesquisando = JOptionPane.showInputDialog("Deseja pesquisar uma disciplina? ('s'para sim ou 'n'para não)");
        while(continuarPesquisando.equals("s")==true){
            retornarDisciplinaInvalida=false;
            pesquisarDisciplina = JOptionPane.showInputDialog(" Pesquise uma disciplina pelo nome: ");
            for(i=0;i<4;i++){
                if (disciplina[i].equals(pesquisarDisciplina)==true){
                    resposta ="Nome: "+disciplina[i]+"\n"+"Professor: "+professor[i]+"\n"+"Carga Horaria: "+cargaHoraria[i];
                    retornarDisciplinaInvalida=true;
                }               
            }
            //legal!
            if(retornarDisciplinaInvalida==false){
                resposta="Disciplina Inválida";
            }
            JOptionPane.showMessageDialog(null, resposta);           
            continuarPesquisando = JOptionPane.showInputDialog("Deseja pesquisar outra disciplina? ('s'para sim ou 'n'para não)");
        }
        JOptionPane.showMessageDialog(null,"Finalizado pelo usuario...");
    }
}