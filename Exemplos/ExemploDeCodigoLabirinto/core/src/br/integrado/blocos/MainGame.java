package br.integrado.blocos;

import com.badlogic.gdx.Game;


public class MainGame extends Game {

	@Override
	public void create() {
		super.setScreen(new TelaJogo(this));
	}
}
