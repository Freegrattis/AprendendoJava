public class Principal {
    public static void main(String[] args) {
        boolean cadastrado = false;

        int opcao = 0;
        ListaTarefas l = new ListaTarefas();
        do {
            opcao = EntradaSaida.escolherOpçãoMenu();
            switch (opcao) {
                case 0:
                    //cadastrar tarefas
                    Tarefas t = new Tarefas();
                    t.descricao = EntradaSaida.receberInformacoesTarefa("a descrição");
                    t.prazo = EntradaSaida.receberInformacoesTarefa("o prazo");
                    t.status = EntradaSaida.receberInformacoesTarefa("o status");
                    t.tipo = EntradaSaida.receberInformacoesTarefa("o tipo");
                    l.adicionarTarefa(t);
                    cadastrado = true;
                    break;
                case 1:
                    EntradaSaida.mostrarTarefas(l.mostrarTarefas());
                //ver todas as tarefas
                    break;
                case 2:
                    EntradaSaida.mostrarTarefas(l.mostrarTarefasAbertas());
                //ver somente em aberto
                    break;
                case 3:
                    EntradaSaida.mostrarTarefas(l.mostrarTarefasConcluidas());
                //ver somente concluídas
                    break;
                case 4:
                if(cadastrado==true){
                    EntradaSaida.mostrarTarefas(l.mostrarTarefas());
                    int posicaoAlterar = EntradaSaida.solicitarPosicao("alterada");
                    l.alterarTarefa(posicaoAlterar-1);
                }else{
                    EntradaSaida.mostrarMensagem("Não há tarefas! ");
                }
                //alterar status de tarefa
                    break;
                case 5:
                if(cadastrado==true){
                    EntradaSaida.mostrarTarefas(l.mostrarTarefas());
                    int posicaoDeletar = EntradaSaida.solicitarPosicao("deletada");
                    l.deletarTarefa(posicaoDeletar-1);
                }else{
                    EntradaSaida.mostrarMensagem("Não há tarefas! ");
                }
                //excluir tarefa
                    break;
                case 6:
                    EntradaSaida.mostrarTarefas(l.mostrarTipos());
                //ver todos os tipos de tarefas
                    break;
                case 7:
                //sair
                    System.exit(0);
                    break;
            }

        } while (opcao != 8);
    }
}
