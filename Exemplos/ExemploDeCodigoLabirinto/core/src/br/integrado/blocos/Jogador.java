package br.integrado.blocos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Elito Fraga on 05/10/2015.
 */
public class Jogador {
    public SpriteBatch batch = new SpriteBatch();
    private Body corpoJogador;
    private Sprite jogador;
    private Texture textDireita;
    private Texture textEsquerda;
    private Texture textCima;
    private Texture textBaixo;
    private float intervalo_frames = 0;
    private final float tempo_intervalo = 0.1f;
    private int estagio = 0;
    private float velocidade = 150;
    public float x = 0;
    public float y = 0;
    public boolean esquerda = false;
    public boolean direita= false;
    public boolean cima = false;
    public boolean baixo = false;
    public Texture texturaJogador;
    public Array<Texture> trocarTexturaDireita = new Array<Texture>();
    public Array<Texture> trocarTexturaEsquerda = new Array<Texture>();
    public Array<Texture> trocarTexturaCima = new Array<Texture>();
    public Array<Texture> trocarTexturaBaixo = new Array<Texture>();

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


    public void desenharJogador(float delta) {
        if ((esquerda) || (direita) || (cima) || (baixo)) {
            if (intervalo_frames >= tempo_intervalo) {
                intervalo_frames = 0;
                estagio ++;
                if (estagio > 3)
                    estagio = 0;
            } else {
                intervalo_frames = intervalo_frames + delta;
            }
        }

        if(esquerda) {
            jogador = new  Sprite(trocarTexturaEsquerda.get(estagio));
            x = x + (jogador.getX() - velocidade * delta);
            y = y + jogador.getY();
        } else if (direita) {
            jogador = new  Sprite(trocarTexturaDireita.get(estagio));
            x = x + (jogador.getX() + velocidade * delta);
            y = y + jogador.getY();
        }else if (cima) {
            jogador = new  Sprite(trocarTexturaCima.get(estagio));
            x = x + jogador.getX();
            y = y + (jogador.getY() + velocidade * delta);
        }else if (baixo) {
            jogador = new  Sprite(trocarTexturaBaixo.get(estagio));
            x = x + jogador.getX();
            y = y + (jogador.getY() - velocidade * delta);
        }else jogador = new Sprite(texturaJogador);

        batch.begin();
        batch.draw(jogador, x, y);
        batch.end();
    }

}

