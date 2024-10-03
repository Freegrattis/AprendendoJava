import java.text.DecimalFormat;
import java.util.ArrayList;

public class Venda {
    private Cliente cliente;
    private String dataHora;
    private ProdutoComprado produtoComprado;
    DecimalFormat df = new DecimalFormat("##,###.00");
  
    public ProdutoComprado getProdutoComprado() {
        return produtoComprado;
    }
    public void setProdutoComprado(ProdutoComprado produtoComprado) {
        this.produtoComprado = produtoComprado;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public String getDataHora() {
        return dataHora;
    }
    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    ArrayList<ProdutoComprado> produtosVendidos = new ArrayList<ProdutoComprado>();

    public void adicionarFerramentasCompradas(ProdutoComprado pc) {
        this.produtosVendidos.add(pc);
    }
    public String percorreVenda(long cpf){
        String retorno = "";
        for(ProdutoComprado pc : this.produtosVendidos){
            if(cpf==this.cliente.getCpf()){
                retorno+="\nFerramenta: "+pc.getFerramenta().getNome()+"\nPreço: R$"+df.format(pc.getFerramenta().getPreco())+"\nQuantidade: "+pc.getQuantidade();
                return retorno;
            }
        }
        return null;
    }
     public double retornaTotal(){
        Double totalVendas = 0.0;
        for(ProdutoComprado pc : this.produtosVendidos){
                totalVendas += pc.getFerramenta().getPreco()*pc.getQuantidade();
            }
        return totalVendas;
    }
}
