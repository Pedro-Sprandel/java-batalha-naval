import java.util.Scanner;

public class Escolha {
    private final static Scanner scanner = new Scanner(System.in);

    public String obterEscolha(String[] textos, String[] opcoes) {
        for (String texto : textos) {
            System.out.println(texto);
        }
        
        String escolha = scanner.nextLine();
        while (!isOpcaoValida(escolha, opcoes)) {
            System.out.println("Opcao invalida, tente novamente");
            escolha = scanner.nextLine();
        }
        
        return escolha;
    }

    private boolean isOpcaoValida(String escolha, String[] opcoes) {
        for (String opcao : opcoes) {
            if (escolha.equals(opcao)) {
                return true;
            }
        }
        return false; 
    }

}