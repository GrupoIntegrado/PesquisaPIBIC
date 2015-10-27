package br.integrado.blocos;

import com.badlogic.gdx.Screen;

/**
 * Created by Elito Fraga on 05/10/2015.
 */
public abstract class TelaBase implements Screen {

    protected MainGame mGame;

    public TelaBase(MainGame game) {
        mGame = game;
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public final void render(float delta) {
        onUpdate(delta);
        onRender(delta);
    }

    protected abstract void onRender(float delta);

    protected abstract void onUpdate(float delta);
}