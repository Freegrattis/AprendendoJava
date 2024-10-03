public class Validacao {
    public static boolean verificaListaVazia(boolean verificador){
        Estoque e = new Estoque();
        if(!e.listaDeFerramentas.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean verificaEstoqueVazio(boolean vazia, int codigoProduto) {
        Estoque e = new Estoque();
        vazia=true;
        for(Ferramentas fs : e.ferramentasEmEstoque){
            if(fs.getQuantidadeAtual()!=0){
                return vazia=false;
            }
                
            }
            return vazia;
        }

    public static boolean verificaCodigoExistente(boolean existe, int codigoFerramenta) {
        Estoque e = new Estoque();
        for(Ferramenta f : e.listaDeFerramentas){
            if(f.getCodigo()==codigoFerramenta){
                return true;
            }
        }
        return false;
    }

    public static int verificaCodigoGerado(int codigoGerado) {
        Estoque e = new Estoque();
        for(Ferramenta f : e.listaDeFerramentas){
            do{
                codigoGerado += 1;
            }while(f.getCodigo() == codigoGerado);
        }
        return codigoGerado;
    }
	public static boolean verificaExclusao(int posicao) {
        if(posicao <= -1 ){
            int opcaoExclusao=EntradaSaida.perguntarExclusaoFerramenta("Não foi possível excluir a ferramenta!");
            if(opcaoExclusao==0){
                return false;
            }else{
                return false;
            }
        }
        else{
            int opcaoExclusao=EntradaSaida.perguntarExclusaoFerramenta("Deseja excluir a ferramenta?");
            if(opcaoExclusao==0){
                return true;
            }else{
                return false;
        }
        }
        

		
	}
}


