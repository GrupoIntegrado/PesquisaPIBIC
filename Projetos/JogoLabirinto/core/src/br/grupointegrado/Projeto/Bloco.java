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

    public Bloco(Vector2 posicao, BlocoTipo  tipo) {
        this.posicao = posicao;
        this.tipo = tipo;
    }

    public Vector2 getPosicao() {
        return posicao;
    }

    public void setPosicao(Vector2 posicao) {
        this.posicao = posicao;
    }

    public BlocoTipo getTipo() {
        return tipo;
    }

    public void setTipo(BlocoTipo tipo) {
        this.tipo = tipo;
    }
}
