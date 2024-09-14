public class Main {
    public static void main(String[] args) {
        Tabuleiro tabuleiro1 = new Tabuleiro();
        tabuleiro1.adicionarNavio("B2", 3, false);
        tabuleiro1.adicionarNavio("C4", 4, true);
        tabuleiro1.adicionarNavio("J1", 1, true);
        tabuleiro1.adicionarNavio("I9", 2, false);
        tabuleiro1.adicionarNavio("G5", 1, false);
        tabuleiro1.printarTabuleiro();
    }
}