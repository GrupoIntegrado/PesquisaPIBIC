package br.grupointegrado.Projeto;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Elito Fraga on 14/09/2015.
 */
public class Jogador {

    private Sprite jogador = new Sprite();
    private TelaJogo telajogo = new TelaJogo(null);
    private SpriteBatch batch = new SpriteBatch();
    private Texture texturaJogador;
    private Texture textDireita;
    private Texture textEsquerda;
    private Texture textCima;
    private Texture textBaixo;
    private float intervalo_frames = 0;
    private final float tempo_intervalo = 0.1f;
    private int estagio = 0;
    private float velocidade = 100;
    private float x = 203;
    private float y = 206;
    private Array<Texture> trocarTexturaDireita = new Array<Texture>();
    private Array<Texture> trocarTexturaEsquerda = new Array<Texture>();
    private Array<Texture> trocarTexturaCima = new Array<Texture>();
    private Array<Texture> trocarTexturaBaixo = new Array<Texture>();

    public void texturas() {
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


    public void atualizarEstagioJogador(float delta) {
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
    }

    float posicaoX = x + 100; float posicaoY = y + 80;
    public void atualizarPosicaoJogador(float delta) {
        if (telajogo.isDireita()) {
            if (x < posicaoX) {
                jogador = new Sprite(trocarTexturaDireita.get(estagio));
                x = x + velocidade * delta;
                y = y + jogador.getY();
                jogador.setPosition(x, y);
            }else {
                telajogo.setDireita(false);
                posicaoX += 100;
            }
        }else if (telajogo.isEsquerda()) {
            if (x > posicaoX) {
                jogador = new Sprite(trocarTexturaEsquerda.get(estagio));
                x = x - velocidade * delta;
                y = y + jogador.getY();
                jogador.setPosition(x, y);
            }else {
                telajogo.setEsquerda(false);
                posicaoX += - 100;
            }
        }else if (telajogo.isCima()) {
            if (y < posicaoY) {
                jogador = new Sprite(trocarTexturaCima.get(estagio));
                x = x + jogador.getX();
                y = y + velocidade * delta;
                jogador.setPosition(x, y);
            }else {
                telajogo.setCima(false);
                posicaoY += 80;
            }
        }else if (telajogo.isBaixo()) {
            if (y > posicaoY) {
                jogador = new Sprite(trocarTexturaBaixo.get(estagio));
                x = x + jogador.getX();
                y = y - velocidade * delta;
                jogador.setPosition(x, y);
            }else {
                telajogo.setBaixo(false);
                posicaoY += - 80;
            }
        }else {
            jogador = new Sprite(texturaJogador);
            jogador.setPosition(x, y);
        }
    }

    public void atualizarJogador(float delta) {
        atualizarEstagioJogador(delta);
        atualizarPosicaoJogador(delta);

        batch.begin();
        batch.draw(jogador, jogador.getX(), jogador.getY());
        batch.end();
    }

    public Sprite getJogador() {
        return jogador;
    }

    public Texture getTexturaJogador() {
        return texturaJogador;
    }

    public void setJogador(Sprite jogador) {
        this.jogador = jogador;
    }

    public Array<Texture> getTrocarTexturaDireita() {
        return trocarTexturaDireita;
    }

    public Array<Texture> getTrocarTexturaEsquerda() {
        return trocarTexturaEsquerda;
    }

    public Array<Texture> getTrocarTexturaCima() {
        return trocarTexturaCima;
    }

    public Array<Texture> getTrocarTexturaBaixo() {
        return trocarTexturaBaixo;
    }
}
