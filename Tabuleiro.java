import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private final List<Navio> navios = new ArrayList<>();
    private int totalCasasAtigidas = 0;
    public int pegarTotalCasasAtingidas() {
        return totalCasasAtigidas;
    }

    static Escolha escolha = new Escolha();

    public void printarTabuleiro() {
        System.out.println();
        for(int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for(int j = 0; j < TAMANHO_TABULEIRO; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printarTabuleiroAoOponente() {
        System.out.println();
        for(int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for(int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (tabuleiro[i][j].contains("■") && !tabuleiro[i][j].equals("X")) {
                    System.out.print("~ ");
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public String gerarCoordenadaAleatoria() {
        Random aleatorio = new Random();
        
        char letra = (char) ('A' + aleatorio.nextInt(10)); 
        int numero = aleatorio.nextInt(10) + 1;
        String coordenada = "" + letra + numero;

        return coordenada;
    }

    public void montarTabuleiroJogador(int jogador) {
        String escolhaAleatorio = escolha.obterEscolha(
            new String[]{"Jogador " + jogador + ", prefere montar o tabuleiro ou gerar aleatório?", "Montar (1), Aleatório (2)"},
            new String[]{"1", "2"}
        );

        if (escolhaAleatorio.equals("1")) {
            System.out.println("Monte o seu tabuleiro");
            montarTabuleiro(false);
        } else {
            System.out.println("Aqui esta seu tabuleiro:");
            montarTabuleiro(true);
        }
    }

    public void montarTabuleiro(boolean eAleatorio) {
        Random aleatorio = new Random();
    
        for (int tamanhoNavio=4; tamanhoNavio > 0; tamanhoNavio--) {
            int quantidadeNavios = 5-tamanhoNavio;

            for (int j=1; j<=quantidadeNavios; j++) {
                Retorno retorno;
                if (eAleatorio) {
                    String coordenada = gerarCoordenadaAleatoria();
                    boolean vertical = aleatorio.nextBoolean();

                    retorno = adicionarNavio(coordenada, tamanhoNavio, vertical);
                } else {
                    printarTabuleiro();

                    System.out.printf("Escolha a posicao inicial do %d navio de %d espacos (ex: C5): ", j, tamanhoNavio);
                    String coordenada = escolha.obterCoordenada(new String[]{}, new ArrayList<>());  

                    String orientacao = tamanhoNavio > 1 ? escolha.obterEscolha(new String[]{"Escolha a orientacao (h ou v): "}, new String[]{"h", "v"}) : "h";

                    retorno = adicionarNavio(coordenada, tamanhoNavio, orientacao.equals("v"));
                }

                if (!retorno.isSucesso()) {
                    if (!eAleatorio) {
                        System.out.println(retorno.getMensagem());
                    }
                    j--;
                }
            }
        }
    }

    private Retorno adicionarNavio(String coordenada, int tamanho, boolean vertical) {
    int latitude = Character.getNumericValue(coordenada.charAt(0)) - 9;
    int longitude = Integer.parseInt(coordenada.substring(1));

    int limite = vertical ? longitude + tamanho : latitude + tamanho;
    if (limite > TAMANHO_TABULEIRO) {
        return new Retorno(false, "Não são permitidos navios fora do tabuleiro");
    }

    for (int i = 0; i < tamanho; i++) {
        int x = vertical ? longitude + i : longitude;
        int y = vertical ? latitude : latitude + i;
        
        if (!tabuleiro[x][y].equals("~")) {
            return new Retorno(false, "Não é permitida a interpolação de navios");
        }
    }

    for (int i = 0; i < tamanho; i++) {
        int x = vertical ? longitude + i : longitude;
        int y = vertical ? latitude : latitude + i;
        tabuleiro[x][y] = "\u001B[3" + tamanho + "m■\u001B[0m";
    }

    navios.add(new Navio(tamanho, longitude, latitude, vertical));
    return new Retorno(true, "");
}

    public Retorno bombardearCoordenada(String coordenada) {
        int x = Integer.parseInt(coordenada.substring(1));
        int y = Character.getNumericValue(coordenada.charAt(0)) - 9;
        boolean atingiu = !tabuleiro[x][y].equals("~");

        if (atingiu) {
            totalCasasAtigidas++;
            tabuleiro[x][y] = "O";
            if (verificarAfundado(x, y)) {
                marcarNavioAfundado(x, y);
                return new Retorno(true, "Navio afundado!");
            }
            return new Retorno(true, "Alvo atingido!");
        }

        tabuleiro[x][y] = "X";
        return new Retorno(true, "Bomba atingiu a agua");
    }

    private boolean verificarAfundado(int x, int y) {
        for (Navio navio : navios) {
            if (navio.verificaPresenca(x, y)) {
                return navio.estaAfundado(tabuleiro, x, y);
            }
        }
        return false;
    }

    private void marcarNavioAfundado(int x, int y) {
        for (Navio navio : navios) {
            if (navio.verificaPresenca(x, y)) {
                for (int i = 0; i < navio.getTamanho(); i++) {
                    int nx = navio.isVertical() ? navio.getInicioX() + i : navio.getInicioX();
                    int ny = navio.isVertical() ? navio.getInicioY() : navio.getInicioY() + i;
                    tabuleiro[nx][ny] = "#";
                }
            }
        }
    }
}
