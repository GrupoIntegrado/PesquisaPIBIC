package br.grupointegrado.Projeto.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import br.grupointegrado.Projeto.MainJogo;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = true;
		config.width = 1105;
		config.height = 644;
		new LwjglApplication(new MainJogo(), config);
	}
}
