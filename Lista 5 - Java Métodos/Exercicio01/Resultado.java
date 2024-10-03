import javax.swing.JOptionPane;
public class Resultado {
    public static void mostrarResultado(double media) {
       if(media>7){
            JOptionPane.showMessageDialog(null, "O aluno está Aprovado!"+"\nMédia: "+media+"\n" );
       }else{
            JOptionPane.showMessageDialog(null,"O aluno está Reprovado!"+"\nMédia: "+media+"\n" );
       }
    }
}
