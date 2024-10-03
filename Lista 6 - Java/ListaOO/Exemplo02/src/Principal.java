public class Principal {
    public static void main(String[] args) {
        int opcao=0;
        Estoque e = new Estoque();
        do{
            opcao=EntradaSaida.escolherOpcaoMenu();
        switch(opcao){
            case 0:
                Peca p = new Peca();
                p.quantidade=EntradaSaida.recebeQuantidade("a quantidade");
                p.descricao=EntradaSaida.recebeCaracteristica("a descrição");
                p.cor=EntradaSaida.recebeCaracteristica("a cor");
                p.altura=EntradaSaida.recebeCaracteristicaNumerica("a altura");
                p.largura=EntradaSaida.recebeCaracteristicaNumerica("a largura");
                p.preco=EntradaSaida.recebeCaracteristicaNumerica("o preço");
                e.adicionarPeca(p);
                
            break;
            case 1:
                EntradaSaida.mostrarPecas(e.listarPecas());
                int posicao=EntradaSaida.solicitaPosicao();
                e.removerPeca(posicao-1);
            break;
            case 2:
                if(!e.listaDePecas.isEmpty()){
                    EntradaSaida.mostrarPecas(e.listarPecas());
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma peça foi inserida!");
                }
            break;
            case 3:
                System.exit(0);
            break;
        }
        }while(opcao!=3);
    }
}
