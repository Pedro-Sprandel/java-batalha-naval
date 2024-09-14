public class Tabuleiro {
    private final int TAMANHO_TABULEIRO = 11;
    public static String[][] tabuleiro = {
            {" ","A","B","C","D","E","F","G","H","I","J"},
            {"1","~","~","~","~","~","~","~","~","~","~"},
            {"2","~","~","~","~","~","~","~","~","~","~"},
            {"3","~","~","~","~","~","~","~","~","~","~"},
            {"4","~","~","~","~","~","~","~","~","~","~"},
            {"5","~","~","~","~","~","~","~","~","~","~"},
            {"6","~","~","~","~","~","~","~","~","~","~"},
            {"7","~","~","~","~","~","~","~","~","~","~"},
            {"8","~","~","~","~","~","~","~","~","~","~"},
            {"9","~","~","~","~","~","~","~","~","~","~"},
            {"X","~","~","~","~","~","~","~","~","~","~"}
    };

    public void printarTabuleiro() {
        for(int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for(int j = 0; j < TAMANHO_TABULEIRO; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public RetornoHandler adicionarNavio(String coordenada, int tamanho, boolean vertical) {
        int latitude = Character.getNumericValue(coordenada.charAt(0)) - 9;
        int longitude = Integer.parseInt(Character.toString(coordenada.charAt(1)));
        if (vertical) {
            if (longitude + tamanho > TAMANHO_TABULEIRO) {
                return new RetornoHandler(false, "Não são permitidos navios fora do tabuleiro");
            }
            for (int i = 0; i < tamanho; i++) {
                if (i > TAMANHO_TABULEIRO || tabuleiro[longitude + i][latitude] != "~") {
                    return new RetornoHandler(false, "Não é permitido interpolação de navios");
                }
            }
            for (int i = 0; i < tamanho; i++) {
                tabuleiro[longitude + i][latitude] = "■";
            }
        } else {
            if (latitude + tamanho - 1 > TAMANHO_TABULEIRO) {
                return new RetornoHandler(false, "Não são permitidos navios fora do tabuleiro");
            }
            for (int i = 0; i < tamanho; i++) {
                if (i > TAMANHO_TABULEIRO || tabuleiro[longitude][latitude + i] != "~") {
                    return new RetornoHandler(false, "Não é permitido interpolação de navios");
                }
            }
            for (int i = 0; i < tamanho; i++) {
                tabuleiro[longitude][latitude + i] = "■";
            }
        }
        return new RetornoHandler(true, "");
    }
}
