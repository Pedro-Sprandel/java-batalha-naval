class Navio {
    private final int tamanho;
    private final int inicioX;
    private final int inicioY;
    private final boolean vertical;

    public Navio(int tamanho, int inicioX, int inicioY, boolean vertical) {
        this.tamanho = tamanho;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
        this.vertical = vertical;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getInicioX() {
        return inicioX;
    }

    public int getInicioY() {
        return inicioY;
    }

    public boolean isVertical() {
        return vertical;
    }

    public boolean verificaPresenca(int x, int y) {
        if (vertical) {
            return x >= inicioX && x < inicioX + tamanho && y == inicioY;
        } else {
            return y >= inicioY && y < inicioY + tamanho && x == inicioX;
        }
    }

    public boolean estaAfundado(String[][] tabuleiro, int xAtual, int yAtual) {
    for (int i = 0; i < tamanho; i++) {
        int x = vertical ? inicioX + i : inicioX; 
        int y = vertical ? inicioY : inicioY + i;
        
        if (!tabuleiro[x][y].equals("O")) {
            return false;
        }
    }
    return true; 
}
}