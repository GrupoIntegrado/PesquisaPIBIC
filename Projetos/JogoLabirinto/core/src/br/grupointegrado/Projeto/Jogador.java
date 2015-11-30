package br.grupointegrado.Projeto;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Elito Fraga on 14/09/2015.
 */
public class Jogador {

    private static final float DESLOCAMENTO_X = 100;
    private static final float DESLOCAMENTO_Y = 80;

    private Sprite sprite;
    private Texture texturaJogador;
    private Texture textDireita;
    private Texture textEsquerda;
    private Texture textCima;
    private Texture textBaixo;
    private float intervalo_frames = 0;
    private final float tempo_intervalo = 0.1f;
    private int estagio = 0;
    private float velocidade = 100;
    private Direcao direcao = Direcao.PARADO;
    private Vector2 posicaoDestino = new Vector2();
    private Array<Texture> trocarTexturaDireita = new Array<Texture>();
    private Array<Texture> trocarTexturaEsquerda = new Array<Texture>();
    private Array<Texture> trocarTexturaCima = new Array<Texture>();
    private Array<Texture> trocarTexturaBaixo = new Array<Texture>();

    public Jogador(float posicaoX, float posicaoY){
        initJogador(posicaoX, posicaoY);
    }

    public void initJogador(float posicaoX, float posicaoY) {
        texturas();
        sprite = new Sprite(texturaJogador);
        sprite.setOrigin(0, 0);
        sprite.setPosition(posicaoX, posicaoY);
    }

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
        if (intervalo_frames >= tempo_intervalo) {
            intervalo_frames = 0;
            estagio ++;
            if (estagio > 3)
                estagio = 0;
        } else {
            intervalo_frames = intervalo_frames + delta;
        }
    }

    public void atualizarPosicaoJogador(float delta) {
        float x, y;
        switch (direcao){
            case DIREITA:
                if (sprite.getX() < posicaoDestino.x) {
                    x = sprite.getX() + velocidade * delta;
                    y = sprite.getY();
                    sprite.setPosition(x, y);
                } else {
                    direcao = Direcao.PARADO;
                }
                break;
            case ESQUERDA:
                if (sprite.getX() > posicaoDestino.x) {
                    x = sprite.getX() - velocidade * delta;
                    y = sprite.getY();
                    sprite.setPosition(x, y);
                }else {
                    direcao = Direcao.PARADO;
                }
                break;
            case CIMA:
                if (sprite.getY() < posicaoDestino.y) {
                    x = sprite.getX();
                    y = sprite.getY() + velocidade * delta;
                    sprite.setPosition(x, y);
                }else {
                    direcao = Direcao.PARADO;
                }
                break;
            case BAIXO:
                if (sprite.getY() > posicaoDestino.y) {
                    x = sprite.getX();
                    y = sprite.getY() - velocidade * delta;
                    sprite.setPosition(x, y);
                }else {
                    direcao = Direcao.PARADO;
                }
                break;
        }
    }

    public void atualizar(float delta, SpriteBatch pincel) {
        if (direcao != Direcao.PARADO) {
            atualizarEstagioJogador(delta);
            atualizarPosicaoJogador(delta);

            switch (direcao) {
                case DIREITA:
                    sprite.setTexture(trocarTexturaDireita.get(estagio));
                    break;
                case ESQUERDA:
                    sprite.setTexture(trocarTexturaEsquerda.get(estagio));
                    break;
                case CIMA:
                    sprite.setTexture(trocarTexturaCima.get(estagio));
                    break;
                case BAIXO:
                    sprite.setTexture(trocarTexturaBaixo.get(estagio));
                    break;
                case PARADO:
                    sprite.setTexture(texturaJogador);
                    break;
            }
        }

        pincel.begin();
        sprite.draw(pincel);
        pincel.end();

    }

    public Texture getTexturaJogador() {
        return texturaJogador;
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

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
        switch (direcao) {
            case DIREITA:
                posicaoDestino.x = sprite.getX() + DESLOCAMENTO_X;
                break;
            case ESQUERDA:
                posicaoDestino.x = sprite.getX() - DESLOCAMENTO_X;
                break;
            case CIMA:
                posicaoDestino.y = sprite.getY() + DESLOCAMENTO_Y;
                break;
            case BAIXO:
                posicaoDestino.y = sprite.getY() - DESLOCAMENTO_Y;
                break;
        }
    }

    public float getX(){
        return sprite.getX();
    }

    public float getY(){
        return sprite.getY();
    }

    public float getWidth(){
        return sprite.getWidth();
    }

    public float getHeight(){
        return sprite.getHeight();
    }
}
