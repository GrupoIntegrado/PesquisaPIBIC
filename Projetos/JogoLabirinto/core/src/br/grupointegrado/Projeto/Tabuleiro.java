package br.grupointegrado.Projeto;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by ADM on 27/08/2016.
 */
public class Tabuleiro {
    private Array<Array<Bloco>> caminho = new Array();
    private float initX = 0;
    private float initY = - 20;
    private Texture texturaBloco;
    private Texture texturaBloco2;
    private Texture texturaAgua;
    private Texture texturaFinal;
    private static float WIDTH_BLOCO = 37;
    private static float HEIGHT_BLOCO = 65;
    private Sprite spriteBloco;

    public void initTextura() {
        texturaBloco = new Texture("Texturas/bloco.png");
        texturaBloco2 = new Texture("Texturas/bloco2.png");
        texturaAgua = new Texture("Texturas/agua.png");
        texturaFinal = new Texture("Texturas/final.png");
    }

    public void redimencionaTextura(){
        spriteBloco = new Sprite(texturaAgua);
        spriteBloco.setSize(WIDTH_BLOCO, HEIGHT_BLOCO);
    }

    public void initNivel(MainJogo jogo) {
        for (BlocoTipo[] linha : jogo.getNivelAtual().getBlocos()) {
            criarLabirinto(linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6], linha[7], linha[8], linha[9], linha[10], linha[11], linha[12]);
        }
    }

    private void criarLabirinto(BlocoTipo... tipos) {
        Array<Bloco> linha = new Array<Bloco>();
        int coluna = caminho.size;
        for (BlocoTipo tipo : tipos) {
            Vector2 posicao = new Vector2(linha.size, coluna);
            switch (tipo) {
                case BLOCO:
                    linha.add(new Bloco(posicao, tipo));
                    break;
                case BLOCO2:
                    linha.add(new Bloco(posicao, tipo));
                    break;
                case AGUA:
                    linha.add(new Bloco(posicao, tipo));
                    break;
                case FINAL:
                    linha.add(new Bloco(posicao, tipo));
                    break;
            }
        }
        caminho.add(linha);
    }

    public void desenhar(SpriteBatch pincel, Jogador jogador) {
        for (int i = caminho.size - 1; i >= 0 ; i--) {
            Array<Bloco> linha = caminho.get(i);
            for (int j = 0; j < linha.size; j++) {
                Bloco bloco=linha.get(j);
                switch (bloco.getTipo()) {
                    case BLOCO:
                        spriteBloco.setTexture(texturaBloco);
                        break;
                    case BLOCO2:
                        spriteBloco.setTexture(texturaBloco2);
                        break;
                    case AGUA:
                        spriteBloco.setTexture(texturaAgua);
                        break;
                    case FINAL:
                        spriteBloco.setTexture(texturaFinal);
                        break;
                }

                atualizaPosTextura(bloco, jogador);
                spriteBloco.draw(pincel);
            }
        }
    }

    private void atualizaPosTextura(Bloco bloco, Jogador jogador) {
        spriteBloco.setPosition(initX + bloco.getPosicao().x * spriteBloco.getWidth(),
                initY + bloco.getPosicao().y * (spriteBloco.getHeight() - 20));

        controlarPosicao(jogador, bloco);
    }

    public boolean atualizaTipoBloco() {
        boolean blocoRemovido = false;
        if (xAtual > xAnterior || xAtual < xAnterior || yAtual > yAnterior || yAtual < yAnterior) {
            if (caminho.get(yAnterior).get(xAnterior).getTipo().equals(BlocoTipo.BLOCO2)) {
                caminho.get(yAnterior).get(xAnterior).setTipo(BlocoTipo.BLOCO);
            } else if (caminho.get(yAnterior).get(xAnterior).getTipo().equals(BlocoTipo.BLOCO)) {
                caminho.get(yAnterior).get(xAnterior).setTipo(BlocoTipo.AGUA);
                blocoRemovido = true;
            }
        }
        atualizar = true;
        return blocoRemovido;
    }

    public int xAtual = 0; public int yAtual = 0;
    public int xAnterior = 0; public int yAnterior = 0;
    private boolean inicio = true; private boolean atualizar;
    private void controlarPosicao(Jogador atJogador, Bloco bloco) {
        Rectangle recJogador = new Rectangle();
        Rectangle recBloco = new Rectangle();

        recBloco.set(initX + bloco.getPosicao().x * spriteBloco.getWidth(),
                initY + 20 + bloco.getPosicao().y * (spriteBloco.getHeight() - 20),
                spriteBloco.getWidth(), spriteBloco.getHeight() - 20);

        recJogador.set(atJogador.getX() + atJogador.getWidth() / 2.7f, atJogador.getY(), atJogador.getWidth() / 3,
                atJogador.getHeight() / 6);

        if (recBloco.contains(recJogador)) {
            if (inicio) {
                xAnterior = (int) bloco.getPosicao().x;
                yAnterior = (int) bloco.getPosicao().y;
                inicio = false;
            } else if (atualizar) {
                xAnterior = xAtual;
                yAnterior = yAtual;
                atualizar = false;
            }
            xAtual = (int) bloco.getPosicao().x;
            yAtual = (int) bloco.getPosicao().y;
        }
    }

    public void disposeTabuleiro() {
        texturaAgua.dispose();
        texturaBloco.dispose();
        texturaBloco2.dispose();
        texturaFinal.dispose();
    }

    public Sprite getSpriteBloco() {
        return spriteBloco;
    }

    public float getInitX() {
        return initX;
    }

    public float getInitY() {
        return initY;
    }

    public int getxAtual() {
        return xAtual;
    }

    public void setxAtual(int xAtual) {
        this.xAtual = xAtual;
    }

    public int getyAtual() {
        return yAtual;
    }

    public void setyAtual(int yAtual) {
        this.yAtual = yAtual;
    }

    public int getxAnterior() {
        return xAnterior;
    }

    public void setxAnterior(int xAnterior) {
        this.xAnterior = xAnterior;
    }

    public int getyAnterior() {
        return yAnterior;
    }

    public void setyAnterior(int yAnterior) {
        this.yAnterior = yAnterior;
    }

    public void setSpriteBloco(Sprite spriteBloco) {
        this.spriteBloco = spriteBloco;
    }
}
