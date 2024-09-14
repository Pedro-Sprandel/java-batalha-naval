public class RetornoHandler {
    private boolean sucesso;
    private String erro;

    public RetornoHandler(boolean sucesso, String erro) {
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
