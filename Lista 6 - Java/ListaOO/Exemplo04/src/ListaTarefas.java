import java.util.ArrayList;

public class ListaTarefas {
    public ArrayList<Tarefas> listaDeTarefas = new ArrayList<Tarefas>();
    
    public void adicionarTarefa(Tarefas t) {
        this.listaDeTarefas.add(t);
    }
    public String mostrarTarefas() {
        String mensagem = "";
        for(Tarefas t : this.listaDeTarefas){
            int posicao = listaDeTarefas.indexOf(t);
            mensagem += "\n\nA informações da tarefa "+((posicao)+1)+"são: ";
            mensagem += "\nDescrição: "+t.descricao+"\nStatus: "+t.status+"\nPrazo: "+t.prazo+"\nTipo: "+t.tipo;
        }
        return mensagem;
    }
    public String mostrarTarefasAbertas() {
        String mensagem = "Não há tarefas em aberto! ";
        for(Tarefas t : this.listaDeTarefas){
            int posicao = listaDeTarefas.indexOf(t);
            if(t.status.equals("Aberto")){
            mensagem = "";
            mensagem += "\n\nA informações da tarefa "+((posicao)+1)+"são: ";
            mensagem += "\nDescrição: "+t.descricao+"\nStatus: "+t.status+"\nPrazo: "+t.prazo+"\nTipo: "+t.tipo;
            }
        }
        return mensagem;
    }
    public String mostrarTarefasConcluidas() {
        String mensagem = "Não há tarefas concluídas! ";
        for(Tarefas t : this.listaDeTarefas){
            int posicao = listaDeTarefas.indexOf(t);
            if(t.status.equals("Concluída")){
            mensagem = "";
            mensagem += "\n\nA informações da tarefa "+((posicao)+1)+"são: ";
            mensagem += "\nDescrição: "+t.descricao+"\nStatus: "+t.status+"\nPrazo: "+t.prazo+"\nTipo: "+t.tipo;
            }
        }
        return mensagem;
    }
    public String mostrarTipos() {
        String mensagem = "Não há tarefas cadastradas! ";
        for(Tarefas t : this.listaDeTarefas){
            if(!listaDeTarefas.isEmpty()){
            mensagem = "";
            mensagem += "\n\nOs tipos de tarefa são: ";
            mensagem += "\nTipo: "+t.tipo;
            }
        }
        return mensagem;
    }
    public String alterarTarefa(int posicaoEscolhida) {
        for(Tarefas t : this.listaDeTarefas){
            int posicao = listaDeTarefas.indexOf(t);
            if(posicao==posicaoEscolhida){
                t.status=EntradaSaida.receberInformacoesTarefa("o novo status");
            }
        }
        return EntradaSaida.mostrarMensagem("O Status foi alterado com sucesso! ");
    }
    public Tarefas deletarTarefa(int posicaoEscolhida) {
        return listaDeTarefas.remove(posicaoEscolhida);
    }
}
