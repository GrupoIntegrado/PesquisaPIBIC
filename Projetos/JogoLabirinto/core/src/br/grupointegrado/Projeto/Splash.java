package br.grupointegrado.Projeto;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by ADM on 29/08/2016.
 */
public class Splash {
    private Sprite splash;
    private float intervalo_frames = 0;
    private int estagio = 0;
    private final float tempo_intervalo = 1 / 8f;
    private Array<Texture> trocarSplash = new Array<Texture>();
    private Texture texturaSplash;
    private Texture texturaSplashBloco;
    private Array<Texture> trocarSplashBloco = new Array<Texture>();
    private Sprite splashBloco;
    private final float tempo_intervaloBloco = 1 / 8f;
    private float intervalo_framesBloco = 0f;
    private int estagioBloco = 0;

    public void initTextura() {
        for (int i = 1; i <= 6; i++) {
            texturaSplash = new Texture("Texturas/splash-" + i + ".png");
            trocarSplash.add(texturaSplash);
        }

        for (int i = 1; i <= 4; i++) {
            texturaSplashBloco = new Texture("Texturas/splashb-" + i + ".png");
            trocarSplashBloco.add(texturaSplashBloco);
        }

        splash = new Sprite(trocarSplash.get(0));
        splashBloco = new Sprite(trocarSplashBloco.get(0));
    }

    private void atualizarPosicaoSplashJogador(Jogador jogador) {
        float posQuedaX = 0;
        float posQuedaY = 0;

        switch (jogador.getDirecao()) {
            case ESQUERDA:
                posQuedaX = jogador.getX() - jogador.getWidth() / 3;
                posQuedaY = jogador.getY();
                splash.setPosition(posQuedaX, posQuedaY);
                break;
            case DIREITA:
                posQuedaX = jogador.getX() + jogador.getWidth() / 3;
                posQuedaY = jogador.getY();
                splash.setPosition(posQuedaX, posQuedaY);
                break;
            case CIMA:
                posQuedaX = jogador.getX();
                posQuedaY = jogador.getY();
                splash.setPosition(posQuedaX, posQuedaY);
                break;
            case BAIXO:
                posQuedaX = jogador.getX();
                posQuedaY = jogador.getY() - jogador.getHeight() / 6;
                splash.setPosition(posQuedaX, posQuedaY);
                break;
        }
    }

    private void atualizarEstagioSplash(float delta) {
        if (intervalo_frames >= tempo_intervalo) {
            if (estagio == 0) {
                //somSplash.play();
            }
            intervalo_frames = 0;
            estagio++;
            if (estagio > 5) {
                estagio = 0;
            }
        } else {
            intervalo_frames = intervalo_frames + delta;
        }
    }

    public void desenharSplashJogador(float delta, SpriteBatch pincel, Jogador jogador) {
        atualizarEstagioSplash(delta);
        atualizarPosicaoSplashJogador(jogador);

        splash.setTexture(trocarSplash.get(estagio));
        splash.setSize(trocarSplash.get(estagio).getWidth() / 2, trocarSplash.get(estagio).getHeight() / 2);

        splash.draw(pincel);
    }

    public void atualizarPosicaoSplashBloco(Jogador jogador, float TAMANHO_LARGURA, float TAMANHO_ALTURA, float xAnterior,
                                            float xAtual, float yAnterior, float yAtual, float initX, float initY) {
        float x = 0;
        float y = 0;

        switch (jogador.getDirecao()) {
            case ESQUERDA:
                x = initX + xAnterior * TAMANHO_LARGURA;
                y = initY + yAtual * (TAMANHO_ALTURA - 21) + TAMANHO_ALTURA / 2;
                splashBloco.setPosition(x, y);
                break;
            case DIREITA:
                x = initX + xAnterior * TAMANHO_LARGURA;
                y = initY + yAtual * (TAMANHO_ALTURA - 21) + TAMANHO_ALTURA / 2;
                splashBloco.setPosition(x, y);
                break;
            case CIMA:
                x = initX + xAtual * TAMANHO_LARGURA;
                y = initY + yAnterior * (TAMANHO_ALTURA - 21) + TAMANHO_ALTURA / 2;
                splashBloco.setPosition(x, y);
                break;
            case BAIXO:
                x = initX + xAtual * TAMANHO_LARGURA;
                y = initY + yAnterior * (TAMANHO_ALTURA - 21) + TAMANHO_ALTURA / 2;
                splashBloco.setPosition(x, y);
                break;
        }
    }

    private void atualizarEstagioSplashBloco(float delta) {
        if (intervalo_framesBloco >= tempo_intervaloBloco) {
            intervalo_framesBloco = 0;
            estagioBloco ++;
            if (estagio > 3) {
                estagio = 0;
            }
        } else {
            intervalo_framesBloco = intervalo_framesBloco + delta;
        }
    }

    public void desenharSplashBloco(float delta, SpriteBatch pincel) {
        atualizarEstagioSplashBloco(delta);

        splashBloco.setTexture(trocarSplashBloco.get(estagioBloco));
        splashBloco.setSize(trocarSplash.get(estagioBloco).getWidth() / 1.2f, trocarSplash.get(estagioBloco).getHeight());

        splashBloco.draw(pincel);
    }

    public void disposeSplash() {
        for (Texture texturaSplash: trocarSplash) {
            texturaSplash.dispose();
        }
        for (Texture texturaSplashBloco: trocarSplashBloco) {
            texturaSplashBloco.dispose();
        }
    }
}
