package br.grupointegrado.Projeto;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Elito Fraga on 08/10/2015.
 */
public class Bloco {
    private BlocoTipo tipo;
    private Vector2 posicao;
    private int vidaBloco;

    public Bloco(Vector2 posicao, BlocoTipo  tipo, int vida) {
        this.posicao = posicao;
        this.tipo = tipo;
        this.vidaBloco = vida;
    }

    public Vector2 getPosicao() {
        return posicao;
    }

    public void setPosicao(Vector2 posicao) {
        this.posicao = posicao;
    }

    public int getVidaBloco() {
        return vidaBloco;
    }

    public void setVidaBloco(int vidaBloco) {
        this.vidaBloco = vidaBloco;
    }

    public BlocoTipo getTipo() {
        return tipo;
    }

    public void setTipo(BlocoTipo tipo) {
        this.tipo = tipo;
    }
}
