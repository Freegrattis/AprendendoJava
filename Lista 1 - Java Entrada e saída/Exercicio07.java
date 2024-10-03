import javax.swing.JOptionPane;

public class Exercicio07 {
    
 /**
 * @param args
 */
public static void main(String[] args) {

        String nomeVendedor = (JOptionPane.showInputDialog("Nome do vendedor: "));
        String mesCalculado = (JOptionPane.showInputDialog("Mês: "));
        int quantidadeVendas = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de vendas: "));
        double valorTotalVendas = Double.parseDouble(JOptionPane.showInputDialog("Valor total de vendas na loja: "));

         double salario = 1500+(350*quantidadeVendas) + (0.00001*valorTotalVendas);

        JOptionPane.showMessageDialog(null, "Confirme os Dados\n"+"Vendedor: "+ nomeVendedor+"\n"+"mes: "+ mesCalculado+"\n"+"Quantidade de vendas: "+ quantidadeVendas+"\n"+"Valor total das vendas: "+ valorTotalVendas);
        JOptionPane.showMessageDialog(null, "Calculo da Folha de Pagamento\n"+"Vendedor: "+ nomeVendedor+"\n"+"Mes: "+ mesCalculado+"\n"+"Valor de suas vendas: R$"+ (350*quantidadeVendas)+"\n"+"Valor recebido das vendas totais: R$"+ (0.00001*valorTotalVendas)+"\n"+"Salario: R$"+salario);

    }

}
