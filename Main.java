import java.util.ArrayList;

public class Main {
    static Escolha escolha = new Escolha();

    private static void jogarSinglePlayer() {
        Tabuleiro tabuleiro1 = new Tabuleiro(); // Tabuleiro do jogador
        Tabuleiro tabuleiroCpu = new Tabuleiro(); // Tabuleiro da CPU

        tabuleiro1.montarTabuleiroJogador(1);
        tabuleiro1.printarTabuleiro();

        tabuleiroCpu.montarTabuleiro(true);
        System.out.println("Tabuleiro CPU gerado e ocultado");

        ArrayList<String> coordenadasJogadas1 = new ArrayList<>();
        ArrayList<String> coordenadasJogadasCpu = new ArrayList<>();
        String jogadorDaVez = "1";

        while (true) {
            System.out.println();
            tabuleiroCpu.printarTabuleiroAoOponente();

            System.out.print("Escolha uma coordenada alvo jogador " + jogadorDaVez + ": ");
            String coordenadaAlvo = escolha.obterCoordenada(new String[]{"Escolha a coordenada ", ""}, coordenadasJogadas1);
            coordenadasJogadas1.add(coordenadaAlvo);

            Retorno retorno = tabuleiroCpu.bombardearCoordenada(coordenadaAlvo);
            String mensagem = retorno.getMensagem();

            if (tabuleiroCpu.pegarTotalCasasAtingidas() >= 20) {
                System.out.println("Vitória do jogador " + jogadorDaVez);
                tabuleiroCpu.printarTabuleiro();
                break;
            }

            if (mensagem.equals("Bomba atingiu a agua")) {
                System.out.println("A bomba não atingiu nada. É a vez da CPU.");
                
                String coordenadaCpu;
                do {
                    coordenadaCpu = tabuleiroCpu.gerarCoordenadaAleatoria();
                } while (coordenadasJogadasCpu.contains(coordenadaCpu));

                coordenadasJogadasCpu.add(coordenadaCpu);
                Retorno retornoCpu = tabuleiro1.bombardearCoordenada(coordenadaCpu);
                String mensagemCpu = retornoCpu.getMensagem();
                System.out.println("CPU escolheu: " + coordenadaCpu + " - " + mensagemCpu);

                if (tabuleiro1.pegarTotalCasasAtingidas() >= 20) {
                    System.out.println("Vitória da CPU!");
                    tabuleiro1.printarTabuleiro();
                    break;
                }
            }
        }
    }

    private static void jogarMultiplayer() {
        Tabuleiro tabuleiro1 = new Tabuleiro();
        Tabuleiro tabuleiro2 = new Tabuleiro();

        tabuleiro1.montarTabuleiroJogador(1);
        tabuleiro1.printarTabuleiro();

        tabuleiro2.montarTabuleiroJogador(2);
        tabuleiro2.printarTabuleiro();

        String jogadorDaVez = "1";
        Tabuleiro oponenteDaVez = tabuleiro2;
        ArrayList<String> coordenadasJogadas1 = new ArrayList<>();
        ArrayList<String> coordenadasJogadas2 = new ArrayList<>();
        while(true) {
            System.out.println();
            oponenteDaVez.printarTabuleiroAoOponente();
            System.out.print("Escolha uma coordenada alvo jogador " + jogadorDaVez + ": ");
            String coordenadaAlvo;
            if (jogadorDaVez.equals("1")) {
                coordenadaAlvo = escolha.obterCoordenada(new String[]{"Escolha a coordenada ", ""}, coordenadasJogadas1);
                coordenadasJogadas1.add(coordenadaAlvo);
            } else {
                coordenadaAlvo = escolha.obterCoordenada(new String[]{"Escolha a coordenada ", ""}, coordenadasJogadas2);
                coordenadasJogadas2.add(coordenadaAlvo);
            }
            Retorno retorno = oponenteDaVez.bombardearCoordenada(coordenadaAlvo);
            String mensagem = retorno.getMensagem();

            if (oponenteDaVez.pegarTotalCasasAtingidas() >= 20) {
                System.out.print("Vitória do jogador " + jogadorDaVez);
                oponenteDaVez.printarTabuleiro();
                break;
            }

            if (mensagem.equals("Bomba atingiu a agua")) {
                jogadorDaVez = (jogadorDaVez.equals("1")) ? "2" : "1";
                oponenteDaVez = (oponenteDaVez == tabuleiro1) ? tabuleiro2 : tabuleiro1;
            }
        }
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