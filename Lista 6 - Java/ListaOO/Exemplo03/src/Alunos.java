import java.util.ArrayList;

public class Alunos {
    public String nome;
    public int serie;
    public int idade;
    public int quantidadeNotas;
    ArrayList<Notas> listaDeNotas=new ArrayList<Notas>();
    
    public void adicionarNotaAluno(Notas n){
        this.listaDeNotas.add(n);
    }
}
