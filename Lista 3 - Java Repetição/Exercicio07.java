import javax.swing.JOptionPane;

public class Exercicio07 {
     public static void main(String[] args) {
        double maiorPeso = 0;
        double maiorAltura = 0;
        String nomeMaisAlta = "";
        String nomeMaisPesada = "";
        for (int i = 6; i > 0; i--) {
            String nome = JOptionPane.showInputDialog("Informe o nome.\n Faltam:" + i+" pessoas.");
            double altura = Double.parseDouble(JOptionPane.showInputDialog("Informe a altura: "));
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Informe o peso: "));
            
            if (maiorPeso < peso) {
                maiorPeso = peso;
                nomeMaisPesada = nome;
            }
            if (maiorAltura<altura){
                maiorAltura=altura;
                nomeMaisAlta = nome;
            }
        }
        JOptionPane.showMessageDialog(null, "Pessoa mais pesada:\n"+"Nome: "+nomeMaisPesada+"\nPeso: "+maiorPeso+" kg\n"+
                                        "Pessoa mais alta:\n"+nomeMaisAlta+"\nAltura: "+maiorAltura+" m");
    }
}
