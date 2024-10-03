import javax.swing.JOptionPane;

public class Exercicio01{

    public static void main(String[] args){

        String nome = JOptionPane.showInputDialog("Informe seu nome: ");
        String sobrenome = JOptionPane.showInputDialog("Informe seu sobrenome: ");
        String diaNascimento = JOptionPane.showInputDialog("Informe o dia do seu nascimento: ");
        String mesNascimento = JOptionPane.showInputDialog("Informe o mês do seu nascimento: ");
        String anoNascimento = JOptionPane.showInputDialog("Informe o ano do seu nascimento: ");
        double pretensaoSalarial = Double.parseDouble(JOptionPane.showInputDialog("Qual sua pretensão salarial? (Em reais.)"));
        String grauInstrucao = JOptionPane.showInputDialog("informe seu grau de instrução: ");
        String cargoPretendido = JOptionPane.showInputDialog("Qual o cargo de sua pretenção: ");
        String possuiCnh = JOptionPane.showInputDialog("Voce possui CNH tipo B?(sim ou nao): ");

        JOptionPane.showMessageDialog(null, "-Confirme os dados Inseridos- \n"+"Nome e sobrenome: "+ nome + " "+sobrenome + "\n" + "Data de nascimento: "
        +diaNascimento+"/"+mesNascimento+"/"+anoNascimento+"\n"+"Pretensão Salarial: R$"
        + pretensaoSalarial+ "\n"+"Grau de Instrução: " + grauInstrucao + "\n"+ "Cargo Pretendido:"
        + cargoPretendido+"\n"+"Possui CNH tipo B: "+possuiCnh);



}

}

