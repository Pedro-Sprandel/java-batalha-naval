public class Retorno {
    private final boolean sucesso;
    private final String erro;

    public Retorno(boolean sucesso, String erro) {
        this.sucesso = sucesso;
        this.erro = erro;
    }

    public boolean isSucesso() {
        return sucesso;
    }
    public String getErro() {
        return erro;
    }
}
