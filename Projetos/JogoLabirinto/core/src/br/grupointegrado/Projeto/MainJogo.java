package br.grupointegrado.Projeto;

import com.badlogic.gdx.Game;

public class MainJogo extends Game{
	
	@Override
	public void create () {

		super.setScreen(new TelaJogo());
	}

}
