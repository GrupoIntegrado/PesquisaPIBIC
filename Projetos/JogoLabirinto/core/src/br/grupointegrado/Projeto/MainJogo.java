package br.grupointegrado.Projeto;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;

public class MainJogo extends Game{

	private Array<Nivel> niveis = new Array<Nivel>();
	int kk;

	private int nivelAtual = 0;
	
	@Override
	public void create () {
		criarNiveis();
		setScreen(new TelaJogo(this));
		System.out.println(kk);
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

	private void criarNiveis() {
		Nivel n = new Nivel();

		n.addLinha(0, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
		n.addLinha(1, BlocoTipo.AGUA, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(2, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(3, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
		n.addLinha(4, BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.AGUA);
		n.addLinha(5, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);

		niveis.add(n);
	}
}
