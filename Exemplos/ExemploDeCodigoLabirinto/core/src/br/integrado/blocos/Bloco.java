package br.integrado.blocos;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Elito Fraga on 01/10/2015.
 */

public class Bloco {

    private Vector2 posicao;
    private BlocoTipo tipo;
    private int vida;
    private boolean estaEmCima;

    public Bloco(Vector2 posicao, BlocoTipo tipo, int vida) {
        this.posicao = posicao;
        this.tipo = tipo;
        this.vida = vida;
        this.estaEmCima = false;
    }

    public int decrementaVida(){
        return vida--;
    }

    public BlocoTipo getTipo() {
        return tipo;
    }

    public void setTipo(BlocoTipo tipo) {
        this.tipo = tipo;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public boolean isEstaEmCima() {
        return estaEmCima;
    }

    public void setEstaEmCima(boolean estaEmCima) {
        this.estaEmCima = estaEmCima;
    }

    public Vector2 getPosicao() {
        return posicao;
    }

    public void setPosicao(Vector2 posicao) {
        this.posicao = posicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bloco bloco = (Bloco) o;

        return !(posicao != null ? !posicao.equals(bloco.posicao) : bloco.posicao != null);
    }

    @Override
    public int hashCode() {
        return posicao != null ? posicao.hashCode() : 0;
    }
}