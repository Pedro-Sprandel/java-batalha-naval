public class Main {
    public static void main(String[] args) {
        Tabuleiro tabuleiro1 = new Tabuleiro();
        
        final RetornoHandler resposta = tabuleiro1.adicionarNavio("B7", 5, true);
        if (!resposta.isSucesso()) {
            System.out.print(resposta.getErro());
        } else {
            tabuleiro1.printarTabuleiro();
        }
    }
}