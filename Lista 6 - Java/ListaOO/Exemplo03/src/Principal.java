public class Principal {
    public static void main(String[] args) {
        
        int opcao=EntradaSaida.solicitarOpcaoMenu();
        ListaAlunos l = new ListaAlunos();
        do{
            opcao=EntradaSaida.escolherOpcaoMenu();
            switch (opcao) {
                case 0:
                    Alunos a = new Alunos();
                    a.nome=EntradaSaida.solicitarCaracteristicas("o nome");
                    a.idade=EntradaSaida.solicitarCaracteristicasNumericas("a idade");
                    a.serie=EntradaSaida.solicitarCaracteristicasNumericas("a serie");

                    int pronto = 0;
                   
                    do{
                    Notas n= new Notas();
                    a.quantidadeNotas += 1; 
                    n.descricaoNota=EntradaSaida.solicitarDescricaoNota(a.quantidadeNotas+"ª");
                    n.nota=EntradaSaida.solicitarNota("a "+a.quantidadeNotas+"ª nota");
                    a.adicionarNotaAluno(n);
                    pronto=EntradaSaida.mostrarAviso();
                    System.out.println(n.nota);
                    }while(pronto!=1);
                    

                    l.adicionarAluno(a);
                    //cadastrar alunos e notas
                    break;
                case 1:
                    if(!l.listaDeAlunos.isEmpty()){
                        EntradaSaida.mostrarAlunos(l.listarAlunos());
                    }
                    else{
                        EntradaSaida.mostrarMensagem("Nenhum aluno inserido! ");
                    }
                    //dados de todos alunos
                    break;
                case 2:
                    String nomeProcurado=EntradaSaida.solicitarCaracteristicas("o nome");
                    String alunoEncontrado=l.procurarAluno(nomeProcurado);
                    EntradaSaida.mostrarMensagem(alunoEncontrado);
                    //dados de aluno especifico
                    break;
                case 3:
                    EntradaSaida.listarNomes(l.listarAlunos());
                    int posicaoSelecionada=EntradaSaida.solicitarPosicao();
                    l.procurarAlunoPosicao(posicaoSelecionada);
                    //alterar nome de um aluno
                    break;
                case 4:
                    EntradaSaida.listarNomes(l.listarAlunos());
                    int posicaoAdicionarNota=EntradaSaida.solicitarPosicao();
                    l.adicionarNotaAlunoPosicao(posicaoAdicionarNota);
                    //Adicione mais uma nota a um aluno
                    break;
                case 5:
                    String nomeEscolhidoMedia=EntradaSaida.solicitarCaracteristicas("o nome");
                    String alunoMediaEscolhida=l.procurarMediaAluno(nomeEscolhidoMedia);
                    EntradaSaida.mostrarMensagem(alunoMediaEscolhida);
                    //Calcular média de aluno específico
                break;
                case 6:
                    System.exit(0);
                    break;
            }
        }while(opcao!=6);
    }
}
