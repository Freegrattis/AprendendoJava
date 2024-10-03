import javax.swing.JOptionPane;

public class Exercicio07 {
    public static void main(String[] args) {

        int escolherPergunta = Integer
                .parseInt(JOptionPane.showInputDialog("Escolha uma pergunta:\n [1]-Paises\n[2]-Ciência basica"));
        String retorno = "Você ";

        // pergunta1
        String pergunta1 = "Que país tem o formato de uma bota?\n[a]-Brasil\n[b]-Itália\n[c]-México";
        // pergunta2
        String pergunta2 = "A que temperatura a água ferve?\n[a]- +200°C\n[b]- +100°C\n[c]- 0°C";
        switch (escolherPergunta) {
            case 1:
                String resposta1 = JOptionPane.showInputDialog(pergunta1);
                resposta1 = resposta1.toLowerCase();
                if (resposta1.equals("b") == true) {
                    retorno += "Acertou";
                } else {
                    retorno += "Errou...";
                }
                break;
            case 2:
                String resposta2 = JOptionPane.showInputDialog(pergunta2);
                resposta2 = resposta2.toLowerCase();
                if (resposta2.equals("b") == true) {
                    retorno += "Acertou";
                } else {
                    retorno += "Errou...";
                }
                break;
        }
        JOptionPane.showMessageDialog(null, retorno);
    }
}
