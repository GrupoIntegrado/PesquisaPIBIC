package br.grupointegrado.Projeto.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.grupointegrado.Projeto.MainJogo;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1020;
		config.height = 577;
		new LwjglApplication(new MainJogo(), config);
	}
}
