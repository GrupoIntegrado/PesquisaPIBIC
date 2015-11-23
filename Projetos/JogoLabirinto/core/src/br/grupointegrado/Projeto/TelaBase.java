package br.grupointegrado.Projeto;

import com.badlogic.gdx.Screen;

/**
 * Created by Elito Fraga on 18/11/2015.
 */
public abstract class TelaBase implements Screen{

    protected MainJogo jogo;

    public TelaBase(MainJogo jogo) {
        this.jogo = jogo;
    }

    @Override
    public void hide() {
        dispose();
    }
}
