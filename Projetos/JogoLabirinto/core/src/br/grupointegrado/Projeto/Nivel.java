package br.grupointegrado.Projeto;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Elito Fraga on 20/11/2015.
 */
public class Nivel {

    private Vector2 posicaoInicial = new Vector2();
    private BlocoTipo[][] blocos = new BlocoTipo[8][13];

    public Vector2 getPosicaoInicial() {
        return posicaoInicial;
    }

    public void setPosicaoInicial(Vector2 posicaoInicial) {
        this.posicaoInicial = posicaoInicial;
    }

    public BlocoTipo[][] getBlocos() {
        return blocos;
    }

    public void setBlocos(BlocoTipo[][] blocos) {
        this.blocos = blocos;
    }

    public void addLinha(int i, BlocoTipo... tipos) {
        for (int j = 0; j < tipos.length; j++) {
            BlocoTipo t = tipos[j];
            blocos[i][j] = t;
        }
    }
}
