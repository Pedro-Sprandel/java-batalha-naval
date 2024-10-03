import java.util.ArrayList;
import java.util.Scanner;

public class Escolha {
    private final static Scanner scanner = new Scanner(System.in);

    private static boolean isCoordenadaValida(String coordenada, ArrayList<String> coordenadasJaUsadas) {
        if (coordenada.length() < 2 || coordenada.length() > 3) {
            return false; 
        }

        for (String coordenadaUsada : coordenadasJaUsadas) {
            if (coordenadaUsada.equals(coordenada)) {
                return false;
            }
        }

        char letra = coordenada.charAt(0);
        String numero = coordenada.substring(1);

        if (letra < 'A' || letra > 'J') {
            return false;
        }

        try {
            int num = Integer.parseInt(numero);
            return (num >= 1 && num <= 10);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String obterCoordenada(String[] textos, ArrayList<String> coordenadasJaUsadas) {
        String coordenada = scanner.nextLine();
        while (!isCoordenadaValida(coordenada, coordenadasJaUsadas)) {
            System.out.println("Opcao invalida, tente novamente");
            coordenada = scanner.nextLine();
        } 

        return coordenada;
    }

    private boolean isOpcaoValida(String escolha, String[] opcoes) {
        for (String opcao : opcoes) {
            if (escolha.equals(opcao)) {
                return true;
            }
        }
        return false; 
    }

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
}