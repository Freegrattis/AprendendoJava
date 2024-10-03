import javax.swing.JOptionPane;

public class Exercicio03 {
    public static void main(String[] args) {
        int bom = 0;
        int otimo = 0;
        int ruim = 0;
        int media = 0;
        int contador = 1;
        String continuar = "s";
        while (continuar.equals("s") == true) {
           int opinião = Integer.parseInt(JOptionPane
                    .showInputDialog(contador
                            + "° pessoa.\nVocê gostou do filme 'Blade Runner 2043'?\n[1]-Ótimo\n[2]-Bom\n[3]-Ruim"));
            while (opinião < 1 || opinião > 3) {
                opinião = Integer.parseInt(JOptionPane
                        .showInputDialog(
                                "Insira uma resposta valida.\nVocê gostou do filme 'Blade Runner 2043'?\n[1]-Ótimo\n[2]-Bom\n[3]-Ruim"));
            }
            switch (opinião) {
                case 1:
                    
                    otimo = otimo + 1;
                    media = media + 1;
                    break;
                case 2:
                    
                    bom = bom + 1;
                    media = media + 2;
                    break;
                case 3:
                   
                    ruim = ruim + 1;
                    media = media + 3;
                    break;
            }
            contador = contador + 1;
            continuar = JOptionPane
                            .showInputDialog("Deseja continuar avaliando? ('S' para sim ou 'N' para não)");
            continuar = continuar.toLowerCase();
        }
        contador = contador -1;
        double porcentagemOtimo = (otimo*100)/contador;
        double porcentagemBom = (bom*100)/contador;
        double porcentagemRuim = (ruim*100)/contador;
        media = media / contador;
        JOptionPane.showMessageDialog(null,
                "Otimo: " + otimo + "/" + porcentagemOtimo + "%" + "\n" + "Bom: " + bom + "/" + porcentagemBom + "%"
                        + "\n" + "Ruim: " + ruim + "/" + porcentagemRuim + "%" + "\n" + "Total de participantes: "
                        + contador + "\n" + "Média de avaliações: " + media);
    }
}
