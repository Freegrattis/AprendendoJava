public class Principal {
    public static void main(String[] args) {
        int opcao;
        Banco b = new Banco();
        
        do{
            opcao=EntradaSaida.escolherOpcaoMenu();
        switch(opcao){
            case 0:
                Produto p = new Produto();
                p.nome=EntradaSaida.receberCaracteristica("o nome");
                p.tamanho=EntradaSaida.receberCaracteristicaNumerica("o tamanho");
                p.custo=EntradaSaida.receberCaracteristicaNumerica("o custo");
                p.peso=EntradaSaida.receberCaracteristicaNumerica("o peso");
                p.naturezaProduto=EntradaSaida.receberCaracteristica("a natureza");
                Marca m = new Marca();
                m.nome=EntradaSaida.receberMarca();

                p.nomeMarca=m;

                System.out.println(p.nome);
                System.out.println(p.tamanho);
                System.out.println(p.custo);
                System.out.println(p.peso);
                System.out.println(p.naturezaProduto); 
                System.out.println(p.nomeMarca.nome);

                b.adicionarProdutos(p);
            break;
            case 1:
                if(b.listaDeProdutos.isEmpty()==false){
                    EntradaSaida.mostrarProdutos(b.listarProdutos());
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhum produto foi adicionado");
                }
            break;
            case 2:
                System.exit(0);
            break;
        }
        }while(opcao!=2);
   }
}
