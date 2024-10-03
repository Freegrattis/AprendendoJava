import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Teste {

    public static void main(String[] args) {
        String[] opcoes = {"Opção 1", "Opção 2"};
        JComboBox combo = new JComboBox<>(opcoes);
        String msg="Mensagem a ser exibida antes do ComboBox";
       
       
        String[] opcoes2 = {"Opção 1", "Opção 2"};
        JComboBox combo2 = new JComboBox<>(opcoes);
        String msg2="Outra mensagem a ser exibida antes do ComboBox";
         Object[] exibicaoUsuario = {msg, combo, msg2, combo2};

        JOptionPane.showMessageDialog(null, exibicaoUsuario);

        
    }
    
}
