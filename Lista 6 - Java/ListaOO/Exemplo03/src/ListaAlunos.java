import java.util.ArrayList;

public class ListaAlunos {
    
    public ArrayList<Alunos> listaDeAlunos = new ArrayList<Alunos>();
    
    public String listarAlunos(){
        String lista="Os alunos cadastrados:\n";
        for(Alunos a : this.listaDeAlunos){
            int posicao = listaDeAlunos.indexOf(a);
            lista+="\nPosição: "+((posicao)+1)+"\nNome do aluno: "+a.nome+"\nIdade: "+a.idade+" anos\nSérie: "+a.serie+"ª série";
            for(int i=0; i<a.quantidadeNotas; i++){
                lista+="\n"+(i+1)+"ª Nota: "+a.listaDeNotas.get(i).nota;
            }
        }
        return lista;
    }
    public void adicionarAluno(Alunos a) {
        this.listaDeAlunos.add(a);
    }
    public String procurarAluno(String nomeProcurado){
        String nomeAlunoEncontrado="Os dados do aluno são:\n";
        for(Alunos a : this.listaDeAlunos){
            if(a.nome.equals(nomeProcurado)){
                int posicao = listaDeAlunos.indexOf(a);
                nomeAlunoEncontrado+="\nPosição: "+((posicao)+1)+"\nNome do aluno: "+a.nome+"\nIdade: "+a.idade+" anos\nSérie: "+a.serie+"ª série";
                for(int i=0; i<a.quantidadeNotas; i++){
                nomeAlunoEncontrado+="\n"+(i+1)+"ª Nota: "+a.listaDeNotas.get(i).nota;
            }
            }
        }
        return nomeAlunoEncontrado;
    }
    public String procurarAlunoPosicao(int posicaoEscolhida){
        for(Alunos a : this.listaDeAlunos){
            int posicao = listaDeAlunos.indexOf(a);
            if(posicao == (posicaoEscolhida-1)){
                String nomeNovo=EntradaSaida.solicitarCaracteristicas("novo nome");
                a.nome=nomeNovo;
            }
        }
        return "nome alterado";
    }
    public String procurarMediaAluno(String nomeProcurado){
        double media = 0;
        String mediaAlunoEncontrado="A média do aluno ";
        for(Alunos a : this.listaDeAlunos){
            if(a.nome.equals(nomeProcurado)){
                mediaAlunoEncontrado+= a.nome;
                for(int i=0; i<a.quantidadeNotas; i++){
                media += a.listaDeNotas.get(i).nota;
            }
            media = media/a.quantidadeNotas;
            mediaAlunoEncontrado+=" é: "+media;
            }
        }
        return mediaAlunoEncontrado;
    }
    public String adicionarNotaAlunoPosicao(int posicaoAdicionarNota) {
        for(Alunos a : this.listaDeAlunos){
            int posicao = listaDeAlunos.indexOf(a);
            if(posicao == (posicaoAdicionarNota-1)){
                Notas n = new Notas();
                a.quantidadeNotas += 1;
                n.descricaoNota=EntradaSaida.solicitarDescricaoNota(a.quantidadeNotas+"ª");
                n.nota=EntradaSaida.solicitarNota("a "+a.quantidadeNotas+"ª nota");
                a.adicionarNotaAluno(n);
            }
        }
        return "nota adicionada";
    }
}

