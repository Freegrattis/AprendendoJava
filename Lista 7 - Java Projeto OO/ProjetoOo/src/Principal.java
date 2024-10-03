import java.text.SimpleDateFormat;
import java.util.Date;
public class Principal {
    public static void main(String[] args) {
        int opcao=0;
        boolean excluir=false;
        boolean erro;
        int codigoCompra=0;
        boolean naoEhNulo=false;
        boolean querComprar=false;
        RegistroVendas rv = new RegistroVendas();
        Estoque e = new Estoque();
        
        do{
            opcao=EntradaSaida.escolherOpcaoMenu();
        switch(opcao){
            case 0:
                //CADASTRAR PRODUTO
                Ferramenta f = new Ferramenta();
                int codigoGerado = (int)(Math.random()*(10000 - 1)) + 1;
                codigoGerado = Validacao.verificaCodigoGerado(codigoGerado);
                f.setCodigo(codigoGerado);
                do{
                        f.setNome(EntradaSaida.receberCaracteristica("o nome"));
                            if(!f.getNome().equals("")){
                                naoEhNulo=true;
                            }else{
                                EntradaSaida.mostrarAlerta("Insira um nome!");
                                naoEhNulo=false;
                        }
                }while(!naoEhNulo);
                do{
                        f.setMarca(EntradaSaida.receberCaracteristica("a marca"));
                            if(!f.getMarca().equals("")){
                                naoEhNulo=true;
                            }else{
                                EntradaSaida.mostrarAlerta("Insira uma marca!");
                                naoEhNulo=false;
                        }
                }while(!naoEhNulo);
                do{
                        f.setDescricao(EntradaSaida.receberCaracteristica("a descrição"));
                            if(!f.getDescricao().equals("")){
                                naoEhNulo=true;
                            }else{
                                EntradaSaida.mostrarAlerta("Insira uma descrição!");
                                naoEhNulo=false;
                        }
                }while(!naoEhNulo);
                do{
                    try{
                        f.setPreco(EntradaSaida.receberCaracteristicaDouble("o preço"));
                        erro=false;
                    } catch (Exception ex){
                        EntradaSaida.mostrarAlerta("Insira somente números!");
                        erro=true;
                    }
                }while(erro);
                e.adicionarFerramenta(f);
                Ferramentas fs = new Ferramentas();
                fs.setFerramenta(f);
                //QUANTIDADE MINIMA
                do{
                    try{
                        fs.setQuantidadeMinima(EntradaSaida.receberCaracteristicaInteiro("a quantidade mínima possível"));
                        erro=false;
                    } catch (Exception ex){
                        EntradaSaida.mostrarAlerta("Insira somente números!");
                        erro=true;
                    }
                }while(erro);                
                e.adicionarFerramentaEstoque(fs);
            break;
            case 1:
                //MOSTRAR PRODUTOS CADASTRADOS
            if(!e.listaDeFerramentas.isEmpty()){
                    EntradaSaida.mostrarTodasFerramentas(e.listarFerramentas());
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma ferramenta foi inserida!");
                }
            break;
            case 2:
               //EDITAR PRODUTO
               if(!e.listaDeFerramentas.isEmpty()){
                    String editado=e.editarFerramentas();
                    EntradaSaida.mostrarAlerta(editado);
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma ferramenta foi inserida!");
                }
            break;
            case 3:
                //EXCLUIR PRODUTO
                if(!e.listaDeFerramentas.isEmpty()){
                    int posicao=e.verificaExclusaoFerramenta();
                    excluir=Validacao.verificaExclusao(posicao);
                    if(excluir==true){
                        e.excluirFerramentas(posicao);
                    }
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma ferramenta foi inserida!");
                }
            break;
            case 4:
                //ADICIONAR QUANTIDADE
                if(!e.listaDeFerramentas.isEmpty()){
                    String saida=e.adicionarQuantidade();
                    EntradaSaida.mostrarAlerta(saida);
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma ferramenta foi inserida!");
                }
            break;
            case 5:
                //RETIRAR QUANTIDADE
                if(!e.listaDeFerramentas.isEmpty()){
                    String msg=e.removerQuantidade();
                    EntradaSaida.mostrarAlerta(msg);
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma ferramenta foi inserida!");
                }
            break;
            case 6:
                //CONSULTAR MARCA
                if(!e.listaDeFerramentas.isEmpty()){
                    EntradaSaida.mostrarFerramentasMarca(e.listarFerramentasMarca());
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma ferramenta foi inserida!");
                }
            break;
            case 7:
                //CONSULTAR CÓDIGO
                if(!e.listaDeFerramentas.isEmpty()){
                    EntradaSaida.mostrarFerramentasCodigo(e.listarFerramentasCodigo());
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma ferramenta foi inserida!");
                }  
            break;
            case 8:
                //VERIFICAR NECESSIDADE DE COMPRA
                if(!e.listaDeFerramentas.isEmpty()){
                    EntradaSaida.mostrarAComprar(e.listarFerramentasComprar());
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma ferramenta foi inserida!");
                }
            break;
            case 9:
            //comprar produto
            if(!e.listaDeFerramentas.isEmpty()){
                
                Cliente c =new Cliente();
                c.setNome(EntradaSaida.receberCaracteristicaCliente("o nome do usuário"));
                do{
                    try{
                        c.setCpf(EntradaSaida.receberCpf());
                        erro=false;
                    } catch (Exception ex){
                        EntradaSaida.mostrarAlerta("Insira somente números!");
                        erro=true;
                    }
                }while(erro);
                querComprar=EntradaSaida.realizarCompra();
                if(querComprar){
                    do{
                        Venda v = new Venda();
                        v.setCliente(c);
                        long horaAtual = System.currentTimeMillis();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        Date date = new Date(horaAtual);
                        v.setDataHora(dateFormat.format(date));
                        codigoCompra=EntradaSaida.receberCaracteristicaInteiro("o codigo");
                        ProdutoComprado pc =new ProdutoComprado();
                        pc.setQuantidade(e.setarQuantidadeProdutoComprado(codigoCompra));
                        pc.setFerramenta(e.setarProdutoComprado(codigoCompra));
                        if(pc.getQuantidade()!=0 || pc.getQuantidade()>0){
                            v.adicionarFerramentasCompradas(pc);
                            rv.realizarVendas(v);
                        }
                    querComprar=EntradaSaida.escolherOpcaoVenda();

                    }while(querComprar);
                }
                else{
                    break;
                }
                
            }else{
                EntradaSaida.mostrarAlerta("Nenhuma ferramenta foi inserida!");
            }
            break;

            case 10:
                //HISTORICO DE VENDAS
                if(!e.listaDeFerramentas.isEmpty()){
                    EntradaSaida.mostrarTodasVendas(rv.listarVendas());
                }
                else{
                    EntradaSaida.mostrarAlerta("Nenhuma venda foi realizada!");
                }
            break;
        }
        }while(opcao!=11);
    }
}
/*
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▓▒▒▒▒░░░░░░░░░░░░░░░░▒▒▓████▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░▓██▓▒▓▓██░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░▒▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░░▒▓▒░░░░░░▒▓▒▒░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░▓███████▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▓▒░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░▒█▓▓▒▒▒▒▒▒▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░▒▒▓▒▒▒▒▒░░░░░▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▓▒░░▒▒▒▒▒▓▓░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░▒▓▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▓██▒░░░▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▓█▓▒░░░░▒▒▒▒▒▒▒▒▒▓▒░░░░░░░░░░░░░
░░░░░░░░░░░░░▓▒▒▒▒▒▒▒▒▒▓█████▓▒░░░░░░░░░░░░░░░░░░░░░░░░▒███▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓░░░░░░░░░░░░
░░░░░░░░░░░▒▓▒▒▒▒▒▒▒▓▓▓▒▒▒▓▓████▒▒░░░░░░░░░░░░░░░░░░░░▓███▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓░░░░░░░░░░░
░░░░░░░░░░▒▓▒▒▒▒▒▒▒▓▒▒▒▒▒████▓░▒▓▓▒▒▒▒░░░░░░░░░░░░░░░░▓█████████▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▓░░░░░░░░░░
░░░░░░░░░░▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒████▒░░░█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒░░░░░░░░░
░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓████▓▒▒▓█▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒░░░░░░░░
░░░░░░░░░▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓█████████▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓░░░░░░░░
░░░░░░░░▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓█████████▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓░░░░░░░
░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█████████▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓░░░░░░░
░░░░░░░▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒░░░░░░
░░░░░░░▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓██▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒░░░░░░
░░░░░░░▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓██▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓░░░░░░
░░░░░░░▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓████▓▓▓█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▒░░░░░░
░░░░░░░░▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓███████▓▓▓▒▒▒░░░░░█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓░░░░░░░
░░░░░░░░▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▓░░░░░░░░░░░░░░░█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓░░░░░░░
░░░░░░░░▒▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓█▓░░░░░░░░░░░▒▒▓██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▒░░░░░░░
░░░░░░░░░▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▓▒▒▒▓▓▓▓████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓░░░░░░░░
░░░░░░░░░▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓█████████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓░░░░░░░░░
░░░░░░░░░░▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓████████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓░░░░░░░░░░
░░░░░░░░░░░▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒████████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▒░░░░░░░░░░
░░░░░░░░░░░░▒▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█████████▓▓▓▓▓█▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒░░░░░░░░░░░
░░░░░░░░░░░░░░▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓█▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▒░░░░░░░░░░░░
░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░▒▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░▒▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▓▓█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░

 */