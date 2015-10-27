package br.grupointegrado.Projeto;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Elito Fraga on 14/09/2015.
 */
public class Jogador {

    private Sprite jogador;
    private TelaJogo telajogo= new TelaJogo();
    private Texture textDireita;
    private Texture textEsquerda;
    private Texture textCima;
    private Texture textBaixo;
    private float intervalo_frames = 0;
    private final float tempo_intervalo = 0.1f;
    private int estagio = 0;
    private float velocidade = 1;
    private float x = 200;
    private float y = 320;
    private Texture texturaJogador;
    private Array<Texture> trocarTexturaDireita = new Array<Texture>();
    private Array<Texture> trocarTexturaEsquerda = new Array<Texture>();
    private Array<Texture> trocarTexturaCima = new Array<Texture>();
    private Array<Texture> trocarTexturaBaixo = new Array<Texture>();

    public void texturasJogador() {
        texturaJogador = new Texture("Texturas/jogador.png");

        for (int textD = 1; textD <= 4; textD++) {
            textDireita = new Texture("Texturas/direita" + textD + ".png");
            trocarTexturaDireita.add(textDireita);
        }
        for (int textE = 1; textE <= 4; textE++) {
            textEsquerda = new Texture("Texturas/esquerda" + textE + ".png");
            trocarTexturaEsquerda.add(textEsquerda);
        }
        for (int textC = 1; textC <= 4; textC++) {
            textCima = new Texture("Texturas/cima" + textC + ".png");
            trocarTexturaCima.add(textCima);
        }
        for (int textB = 1; textB <= 4; textB++) {
            textBaixo = new Texture("Texturas/baixo" + textB + ".png");
            trocarTexturaBaixo.add(textBaixo);
        }
    }

    private int i = 0; private int j = 0; float posicaoX = x + 100; float posicaoY = y + 80;
    public void atualizarJogador(float delta) {

        if (telajogo.capiturarTeclas()) {
            if (intervalo_frames >= tempo_intervalo) {
                intervalo_frames = 0;
                estagio ++;
                if (estagio > 3)
                    estagio = 0;
            } else {
                intervalo_frames = intervalo_frames + delta;
            }
        }

        if(telajogo.isEsquerda()) {
            if (x > posicaoX) {
                jogador = new Sprite(trocarTexturaEsquerda.get(estagio));
                x = x - velocidade;
                y = y + jogador.getY();
            }else {
                telajogo.setEsquerda(false);
                posicaoX = posicaoX - 100;
            }
        } else if (telajogo.isDireita()) {
            if  (x < posicaoX) {
                jogador = new  Sprite(trocarTexturaDireita.get(estagio));
                x = x + velocidade;
                y = y + jogador.getY();
            }else {
                telajogo.setDireita(false);
                posicaoX = posicaoX + 100;
            }
        }else if (telajogo.isCima()) {
            if (y < posicaoY) {
                jogador = new Sprite(trocarTexturaCima.get(estagio));
                x = x + jogador.getX();
                y = y + velocidade;
            }else {
                telajogo.setCima(false);
                posicaoY = posicaoY + 80;
            }
        }else if (telajogo.isBaixo()) {
            if (y > posicaoY) {
                jogador = new Sprite(trocarTexturaBaixo.get(estagio));
                x = x + jogador.getX();
                y = y  - velocidade;
            }else {
                telajogo.setBaixo(false);
                posicaoY = posicaoY - 80;
            }
        }else jogador = new Sprite(texturaJogador);

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Sprite getJogador() {
        return jogador;
    }

    public Texture getTexturaJogador() {
        return texturaJogador;
    }

    public void setTexturaJogador(Texture texturaJogador) {
        this.texturaJogador = texturaJogador;
    }

    public void setJogador(Sprite jogador) {
        this.jogador = jogador;
    }

    public Array<Texture> getTrocarTexturaDireita() {
        return trocarTexturaDireita;
    }

    public void setTrocarTexturaDireita(Array<Texture> trocarTexturaDireita) {
        this.trocarTexturaDireita = trocarTexturaDireita;
    }

    public Array<Texture> getTrocarTexturaEsquerda() {
        return trocarTexturaEsquerda;
    }

    public void setTrocarTexturaEsquerda(Array<Texture> trocarTexturaEsquerda) {
        this.trocarTexturaEsquerda = trocarTexturaEsquerda;
    }

    public Array<Texture> getTrocarTexturaCima() {
        return trocarTexturaCima;
    }

    public void setTrocarTexturaCima(Array<Texture> trocarTexturaCima) {
        this.trocarTexturaCima = trocarTexturaCima;
    }

    public Array<Texture> getTrocarTexturaBaixo() {
        return trocarTexturaBaixo;
    }

    public void setTrocarTexturaBaixo(Array<Texture> trocarTexturaBaixo) {
        this.trocarTexturaBaixo = trocarTexturaBaixo;
    }

}
