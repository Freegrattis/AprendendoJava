import java.text.DecimalFormat;
import java.util.ArrayList;

public class Estoque {
    ArrayList<Peca> listaDePecas = new ArrayList<Peca>();

    public void adicionarPeca(Peca p) {
        this.listaDePecas.add(p);
    }

    public String listarPecas(){
        String pecas="Os dados da peça são\n";
        DecimalFormat df = new DecimalFormat("#,###.00");

        for(Peca p : this.listaDePecas){
            int posicao = listaDePecas.indexOf(p);
            pecas+="\nPosição: "+((posicao)+1)+"\nDescrição: "+p.descricao+"\nCor: "+p.cor+"\nAltura: "+df.format(p.altura)+" cm\nLargura: "+df.format(p.largura)+" cm\nPreço: R$"+df.format(p.preco);
        }

        return pecas;
    }

    public Peca removerPeca(int posicao){
        return listaDePecas.remove(posicao);
}
}
