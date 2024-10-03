import java.text.DecimalFormat;
import java.util.ArrayList;

public class Banco {
    public ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();

    public void adicionarProdutos(Produto p){
        this.listaDeProdutos.add(p);
    }

    public String listarProdutos(){
        String produtos="Os produtos cadastrados são:\n";
        DecimalFormat df = new DecimalFormat("#,###.00");

        for(Produto p : this.listaDeProdutos){
            produtos+="\nMarca: "+p.nomeMarca.nome+"\nNome: "+ p.nome+"\nTamanho: "+df.format(p.tamanho)+" cm\nPreço: R$"+df.format(p.custo)+"\nPeso: "+df.format(p.peso)+" Kg\nNatureza do produto: "+p.naturezaProduto;
        }
        return produtos;
    }
}
