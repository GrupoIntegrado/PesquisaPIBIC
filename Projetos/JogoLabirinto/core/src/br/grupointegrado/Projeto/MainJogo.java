package br.grupointegrado.Projeto;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class MainJogo extends Game{

	public static int WIDTH = 640, HEIGHT = 360;

	private Array<Nivel> niveis = new Array<Nivel>();

	private int nivelAtual = 0;

	@Override
	public void create () {
		criarNiveis();
		setScreen(new Menu(this));
		//setScreen(new TelaJogo(this));
}

	public Array<Nivel> getNiveis() {
		return niveis;
	}

	public void setNiveis(Array<Nivel> niveis) {
		this.niveis = niveis;
	}

	public Nivel getNivelAtual() {
		return niveis.get(nivelAtual);
	}

	public int getNivelAtualIndex() {
		return nivelAtual;
	}

	public void setNivelAtual(int nivelAtual) {
		this.nivelAtual = nivelAtual;
	}

	private void criarNiveis() {

		Nivel n = new Nivel();

		n.setPosicaoInicial(new Vector2(4, 5));

        n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(8, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

		n = new Nivel();

		n.setPosicaoInicial(new Vector2(6, 7));

		n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
        n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(8, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

		n = new Nivel();

		n.setPosicaoInicial(new Vector2(3, 4));

		n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
        n.addLinha(8, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

		n = new Nivel();

		n.setPosicaoInicial(new Vector2(4, 6));

        n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.FINAL, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(8, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

		n = new Nivel();

		n.setPosicaoInicial(new Vector2(2, 8));

        n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(8, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

		n = new Nivel();

		n.setPosicaoInicial(new Vector2(2, 6));

		n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.FINAL, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
        n.addLinha(8, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);


		n = new Nivel();

		n.setPosicaoInicial(new Vector2(10, 5));


        n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
        n.addLinha(8, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

		n = new Nivel();

		n.setPosicaoInicial(new Vector2(8, 3));

		n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
        n.addLinha(8, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

		n = new Nivel();

		n.setPosicaoInicial(new Vector2(4, 2));

		n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
        n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
        n.addLinha(8, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

	}
}
