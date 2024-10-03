import javax.swing.JOptionPane;

public class Exercicio01 {
    public static void main(String[] args) {
        int pares = 0;
        int validarPares = 0;
        int positivo = 0;
        for (int i = 10; i > 0; i--) {
            int numerosEscolhidos = Integer
                    .parseInt(JOptionPane.showInputDialog("Informe 10 numeros inteiros.\n Faltam:" + i));
            validarPares = numerosEscolhidos % 2;
            if (validarPares == 0) {
                pares = pares + 1;

            } else if (numerosEscolhidos >= 0) {
                positivo = positivo + 1; //poderia ser positivo++, só uma dica
            }

        }

        int opcaoMenu = Integer.parseInt(JOptionPane.showInputDialog("ESCOLHA UMA OPÇÂO\n" + "[1]-Pares\n"
                + "[2]-Impares\n" + "[3]-Positivos\n" + "[4]-Negativo\n" + "[5]-Sair"));
        while (opcaoMenu < 1 || opcaoMenu > 5) {
            opcaoMenu = Integer.parseInt(JOptionPane.showInputDialog("ESCOLHA UMA OPÇÃO VÁLIDA\n" + "[1]-Pares\n"
                    + "[2]-Impares\n" + "[3]-Positivos\n" + "[4]-Negativo\n" + "[5]-Sair"));
        }

        switch (opcaoMenu) {
            case 1:
                JOptionPane.showMessageDialog(null, "Pares: " + pares);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Impares: " + (10 - pares));
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Positivos: " + positivo);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Negativo: " + (10 - positivo));
                break;
            case 5:
                System.exit(0);
                break;
        }

    }
}