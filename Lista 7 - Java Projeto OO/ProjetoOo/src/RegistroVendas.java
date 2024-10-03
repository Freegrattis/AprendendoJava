import java.text.DecimalFormat;
import java.util.ArrayList;
public class RegistroVendas {
    
    ArrayList<Venda> listaVendas = new ArrayList<Venda>();

    public void realizarVendas(Venda v) {
        this.listaVendas.add(v);
    }
    public String listarVendas(){
        DecimalFormat df = new DecimalFormat("##,###.00");
        double total = 0.0;
        String resultado="\nHISTÓRICO DE COMPRAS:";
        for(Venda v : this.listaVendas){
            resultado+="\n\nCliente: "+v.getCliente().getNome()+"\nCPF: "+v.getCliente().getCpf()+"\nData de compra: "+v.getDataHora();
            resultado+=v.percorreVenda(v.getCliente().getCpf());
            total += v.retornaTotal();
            }
        if(resultado.equals("\nHISTÓRICO DE COMPRAS:\n")){
            resultado="Não foram realizadas compras!";
        }
        resultado+="\n\nTotal: R$"+df.format(total);
    return resultado;
    }
}

