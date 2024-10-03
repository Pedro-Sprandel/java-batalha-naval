public class Main {
    static Escolha escolha = new Escolha();

    private static void montarTabuleiroJogador(Tabuleiro tabuleiro, int jogador) {
        String escolhaAleatorio = escolha.obterEscolha(
            new String[]{"Jogador " + jogador + ", prefere montar o tabuleiro ou gerar aleatório?", "Montar (1), Aleatório (2)"},
            new String[]{"1", "2"}
        );

        if (escolhaAleatorio.equals("1")) {
            System.out.println("Monte o seu tabuleiro");
            tabuleiro.montarTabuleiro(false);
        } else {
            System.out.println("Aqui esta seu tabuleiro:");
            tabuleiro.montarTabuleiro(true);
        }
    }

    private static void jogarSinglePlayer() {
        Tabuleiro tabuleiro1 = new Tabuleiro();
        Tabuleiro tabuleiroCpu = new Tabuleiro();

        montarTabuleiroJogador(tabuleiro1, 1);
        tabuleiroCpu.montarTabuleiro(true);
    }

    private static void jogarMultiplayer() {
        Tabuleiro tabuleiro1 = new Tabuleiro();
        Tabuleiro tabuleiro2 = new Tabuleiro();

        montarTabuleiroJogador(tabuleiro1, 1);
        montarTabuleiroJogador(tabuleiro2, 2);
    }

    public static void main(String[] args) {
        String modoDeJogo = escolha.obterEscolha(new String[]{"Escolha o modo de jogo", "VS CPU (1), Multiplayer (2)"}, new String[]{"1", "2"});
        if (modoDeJogo.equals("1")) {
            jogarSinglePlayer();
        } else {
            jogarMultiplayer();
        }
    }
}