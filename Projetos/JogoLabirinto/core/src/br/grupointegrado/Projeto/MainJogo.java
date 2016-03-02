package br.grupointegrado.Projeto;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MainJogo extends Game{

	private Array<Nivel> niveis = new Array<Nivel>();

	private int nivelAtual = 0;
	private boolean inicioJogo = false;
	
	@Override
	public void create () {
		criarNiveis();
		setScreen(new TelaJogo(this));
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

	public boolean isInicioJogo() {
		return inicioJogo;
	}

	public void setInicioJogo(boolean inicioJogo) {
		this.inicioJogo = inicioJogo;
	}

	private void criarNiveis() {

		Nivel n = new Nivel();

		n.setPosicaoInicial(new Vector2(5, 7));
		n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

		n = new Nivel();

		n.setPosicaoInicial(new Vector2(3, 4));
		n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

		n = new Nivel();

		n.setPosicaoInicial(new Vector2(3, 4));
		n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.FINAL, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(6, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(7, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		niveis.add(n);

	}
}
