package br.grupointegrado.Projeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Elito Fraga on 14/09/2015.
 */
public class Jogador {

    private static float DESLOCAMENTO_X = 0;
    private static float DESLOCAMENTO_Y = 0;

    private Sprite sprite;
    private Texture texturaJogador;
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
    private static float WIGTH_JOGADOR = 27;
    private static float HEIGHT_JOGADOR = 48;

    private Sound somMovJogador;

    public Jogador(float posicaoX, float posicaoY, float disX, float disY){
        initTextura();
        sprite = new Sprite(texturaJogador);
        sprite.setSize(WIGTH_JOGADOR, HEIGHT_JOGADOR);
        sprite.setPosition(posicaoX - (sprite.getWidth() / 2), posicaoY);
        DESLOCAMENTO_X = disX;
        DESLOCAMENTO_Y = disY;
    }


    public void initTextura() {
        texturaJogador = new Texture("Texturas/jogador.png");

        for (int textD = 1; textD <= 4; textD++) {
            Texture textDireita = new Texture("Texturas/direita" + textD + ".png");
            trocarTexturaDireita.add(textDireita);
        }
        for (int textE = 1; textE <= 4; textE++) {
            Texture textEsquerda = new Texture("Texturas/esquerda" + textE + ".png");
            trocarTexturaEsquerda.add(textEsquerda);
        }
        for (int textC = 1; textC <= 4; textC++) {
            Texture textCima = new Texture("Texturas/cima" + textC + ".png");
            trocarTexturaCima.add(textCima);
        }
        for (int textB = 1; textB <= 4; textB++) {
            Texture textBaixo = new Texture("Texturas/baixo" + textB + ".png");
            trocarTexturaBaixo.add(textBaixo);
        }
    }

    private void redimencionaTextura() {
        sprite = new Sprite(texturaJogador);
        sprite.setSize(WIGTH_JOGADOR, HEIGHT_JOGADOR);
    }


    public void initSom() {
        somMovJogador = Gdx.audio.newSound(Gdx.files.internal("Sound/movpersonagem.mp3"));
    }

    public void atualizarEstagioTextura(float delta) {
        if (intervalo_frames >= tempo_intervalo) {
            //somMovJogador.play();
            intervalo_frames = 0;
            estagio ++;
            if (estagio > 3) {
                estagio = 0;
            }
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
                    sprite.setPosition(sprite.getX() - 1, sprite.getY());
                    setDirecao(Direcao.PARADO);
                }
                break;
            case ESQUERDA:
                if (sprite.getX() > posicaoDestino.x) {
                    x = sprite.getX() - velocidade * delta;
                    y = sprite.getY();
                    sprite.setPosition(x, y);
                }else {
                    sprite.setPosition(sprite.getX() + 1, sprite.getY());
                    setDirecao(Direcao.PARADO);
                }
                break;
            case CIMA:
                if (sprite.getY() < posicaoDestino.y) {
                    x = sprite.getX();
                    y = sprite.getY() + velocidade * delta;
                    sprite.setPosition(x, y);
                }else {
                    sprite.setPosition(sprite.getX(), sprite.getY() - 1);
                    setDirecao(Direcao.PARADO);
                }
                break;
            case BAIXO:
                if (sprite.getY() > posicaoDestino.y) {
                    x = sprite.getX();
                    y = sprite.getY() - velocidade * delta;
                    sprite.setPosition(x, y);
                }else {
                    sprite.setPosition(sprite.getX(), sprite.getY() + 1);
                    setDirecao(Direcao.PARADO);
                }
                break;
        }
    }

    public void desenhar(float delta, SpriteBatch pincel) {
        if (direcao != Direcao.PARADO) {
            atualizarEstagioTextura(delta);
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
            }
        }
        sprite.draw(pincel);
    }

    public void setDirecao(Direcao direcao) {
        switch (direcao) {
            case DIREITA:
                posicaoDestino.x = sprite.getX() + DESLOCAMENTO_X;
                System.out.println(posicaoDestino.x);
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
            case PARADO:
                break;
        }
        this.direcao = direcao;
    }

    public void disposeJogador() {
        texturaJogador.dispose();

        for (Texture texturasMovimento : trocarTexturaDireita) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : trocarTexturaEsquerda) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : trocarTexturaCima) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : trocarTexturaBaixo) {
            texturasMovimento.dispose();
        }

    }

    public Direcao getDirecao() {
        return direcao;
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
