import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {
    private final static int TAMANHO_TABULEIRO = 11;
    private final String[][] tabuleiro = {
            {"  ","A","B","C","D","E","F","G","H","I","J"},
            {"1 ","~","~","~","~","~","~","~","~","~","~"},
            {"2 ","~","~","~","~","~","~","~","~","~","~"},
            {"3 ","~","~","~","~","~","~","~","~","~","~"},
            {"4 ","~","~","~","~","~","~","~","~","~","~"},
            {"5 ","~","~","~","~","~","~","~","~","~","~"},
            {"6 ","~","~","~","~","~","~","~","~","~","~"},
            {"7 ","~","~","~","~","~","~","~","~","~","~"},
            {"8 ","~","~","~","~","~","~","~","~","~","~"},
            {"9 ","~","~","~","~","~","~","~","~","~","~"},
            {"10","~","~","~","~","~","~","~","~","~","~"}
    };

    private void printarTabuleiro() {
        for(int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for(int j = 0; j < TAMANHO_TABULEIRO; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isCoordenadaValida(String coordenada) {
        if (coordenada.length() < 2 || coordenada.length() > 3) {
            return false; 
        }

        char letra = coordenada.charAt(0);
        String numero = coordenada.substring(1);

        if (letra < 'A' || letra > 'J') {
            return false;
        }

        if (numero.equals("X")) {
            return true; 
        } else {
            try {
                int num = Integer.parseInt(numero);
                return (num >= 1 && num <= 10);
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public void montarTabuleiro(boolean eAleatorio) {
        Scanner scanner = new Scanner(System.in);
        Random aleatorio = new Random();
        for (int tamanhoNavio=5; tamanhoNavio > 0; tamanhoNavio--) {

            int quantidadeNavios = 6-tamanhoNavio;

            for (int j=1; j<=quantidadeNavios; j++) {
                Retorno retorno;
                if (eAleatorio) {
                    char letra = (char) ('A' + aleatorio.nextInt(10)); 
                    int numero = aleatorio.nextInt(10) + 1;
                    String coordenada = "" + letra + numero;

                    boolean vertical = aleatorio.nextBoolean();

                    retorno = adicionarNavio(coordenada, tamanhoNavio, vertical);
                } else {
                    printarTabuleiro();
                    System.out.printf("Escolha a posicao inicial do %d navio de %d espacos (ex: C5): ", j, tamanhoNavio);

                    String coordenada = scanner.nextLine();
                    while (!isCoordenadaValida(coordenada)) {
                        System.out.println("Opcao invalida, tente novamente");
                        coordenada = scanner.nextLine();
                    }   

                    System.out.print("Escolha a orientacao (h ou v): ");
                    String orientacao = scanner.nextLine();
                    while (!orientacao.equals("h") && !orientacao.equals("v")) {
                        System.out.println("Opcao invalida, tente novamente");
                        orientacao = scanner.nextLine();
                    }

                    retorno = adicionarNavio(coordenada, tamanhoNavio, orientacao.equals("v"));
                }

                if (!retorno.isSucesso()) {
                    if (!eAleatorio) {
                        System.out.println(retorno.getErro());
                    }
                    j--;
                }
            }
        }
        printarTabuleiro();
    }

    private Retorno adicionarNavio(String coordenada, int tamanho, boolean vertical) {
        int latitude = Character.getNumericValue(coordenada.charAt(0)) - 9;
        int longitude = Integer.parseInt(coordenada.substring(1));
        if (vertical) {
            if (longitude + tamanho > TAMANHO_TABULEIRO) {
                return new Retorno(false, "Não são permitidos navios fora do tabuleiro");
            }
            for (int i = 0; i < tamanho; i++) {
                if (i >= TAMANHO_TABULEIRO || !tabuleiro[longitude + i][latitude].equals("~")) {
                    return new Retorno(false, "Não é permitida a interpolação de navios");
                }
            }
            for (int i = 0; i < tamanho; i++) {
                tabuleiro[longitude + i][latitude] = "■";
            }
        } else {
            if (latitude + tamanho > TAMANHO_TABULEIRO) {
                return new Retorno(false, "Não são permitidos navios fora do tabuleiro");
            }
            for (int i = 0; i < tamanho; i++) {
                if (i >= TAMANHO_TABULEIRO || !tabuleiro[longitude][latitude + i].equals("~")) {
                    return new Retorno(false, "Não é permitida a interpolação de navios");
                } 
            }
            for (int i = 0; i < tamanho; i++) {
                tabuleiro[longitude][latitude + i] = "■";
            }
        }
        return new Retorno(true, "");
    }
}
