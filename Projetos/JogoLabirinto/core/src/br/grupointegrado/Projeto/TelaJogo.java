package br.grupointegrado.Projeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import jdk.nashorn.internal.ir.BlockLexicalContext;


/**
 * Created by Elito Fraga on 10/09/2015.
 */
public class TelaJogo implements Screen {

    private OrthographicCamera camera;
    private Jogador classJogador;
    private Texture texturaBloco;
    private Texture texturaBloco2;
    private Texture texturaAgua;
    private Texture texturaFinal;
    private SpriteBatch batch;
    private boolean parado = false;
    private boolean esquerda = false;
    private boolean direita = false;
    private boolean cima = false ;
    private boolean baixo = false;
    private Array<Array<Bloco>> caminho = new Array<Array<Bloco>>();


    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        classJogador = new Jogador();
        classJogador.texturasJogador();
        classJogador.setJogador(new Sprite());

        texturasCaminho();
        tipoBlocos();
    }

    private void texturasCaminho() {
        texturaBloco = new Texture("Texturas/bloco.png");
        texturaAgua = new Texture("Texturas/agua.png");
        texturaFinal = new Texture("Texturas/final.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        capiturarTeclas();
        atualizarCaminho();
        classJogador.atualizarJogador(delta);
    }

    private void tipoBlocos() {
        criarCaminho(BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
        criarCaminho(BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
        criarCaminho(BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO,BlocoTipo.AGUA);
        criarCaminho(BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
    }

    public void criarCaminho(BlocoTipo... tipos) {
        Array<Bloco> linha = new Array<Bloco>();
        int coluna = caminho.size;
        for (BlocoTipo tipo : tipos) {
            Vector2 posicao = new Vector2(linha.size, coluna);
            switch (tipo) {
                case BLOCO:
                    linha.add(new Bloco(posicao, tipo, 1));
                    break;
                case AGUA:
                    linha.add(new Bloco(posicao, tipo, -1));
                    break;
                case FINAL:
                    linha.add(new Bloco(posicao, tipo, -1));
                    break;
            }
        }
        caminho.add(linha);
    }

    private float initX = 80; private float initY = 200;
    private Texture textura;
    private Rectangle recJogador = new Rectangle();
    private Rectangle recBloco = new Rectangle();

    private void atualizarCaminho() {
        recJogador.set(classJogador.getX(), classJogador.getY(), classJogador.getJogador().getWidth()  / 2,
                classJogador.getJogador().getHeight() / 2);
        for (int i = 0; i < caminho.size; i++) {
            Array<Bloco> linha = caminho.get(i);
            for (int j = 0; j < linha.size; j++) {
                Bloco bloco = linha.get(j);

                switch (bloco.getTipo()) {
                    case BLOCO:
                        textura = texturaBloco;
                    break;
                    case BLOCO2:
                        textura = texturaBloco2;
                    break;
                    case AGUA:
                        textura = texturaAgua;
                        break;
                    case FINAL:
                        textura = texturaFinal;
                        break;
                }

                recBloco.set(initX + bloco.getPosicao().x * textura.getWidth(), initY + bloco.getPosicao().y * textura.getHeight(),
                        textura.getWidth(), textura.getHeight());


                removerBlocos(bloco);

                batch.begin();
                batch.draw(textura, initX + bloco.getPosicao().x * textura.getWidth(),
                        initY + bloco.getPosicao().y * textura.getHeight());
                batch.end();
            }
        }
    }

    private int xAnterior = 1; private int yAnterior = 1;
    private void removerBlocos(Bloco bloco) {
        
        if (recBloco.contains(recJogador)) {
            int xAtual = (int) bloco.getPosicao().x;
            int yAtual = (int) bloco.getPosicao().y;

            if (yAnterior < yAtual) {
                yAnterior = yAtual - 1;
                caminho.get(yAnterior).get(xAtual).setTipo(BlocoTipo.AGUA);
                yAnterior = yAnterior + 1;
                xAnterior = xAtual;
            }else if (yAnterior > yAtual) {
                yAnterior = yAtual + 1;
                caminho.get(yAnterior).get(xAtual).setTipo(BlocoTipo.AGUA);
                yAnterior = yAnterior - 1;
                xAnterior = xAtual;
            }else if (xAnterior < xAtual) {
                xAnterior = xAtual - 1;
                caminho.get(yAtual).get(xAnterior).setTipo(BlocoTipo.AGUA);
                xAnterior = xAtual;
            }else if (xAnterior > xAtual) {
                caminho.get(yAtual).get(xAnterior).setTipo(BlocoTipo.AGUA);
                xAnterior = xAtual;
            }
        }
    }

    public boolean capiturarTeclas() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            esquerda = true;
            return esquerda;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direita = true;
            return direita;
        }else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cima = true;
            return cima;
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            baixo = true;
            return baixo;
        }
        return parado = true;
    }



    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        classJogador.getTexturaJogador().dispose();
        texturaBloco.dispose();
        texturaAgua.dispose();
        for (Texture texturasMovimento : classJogador.getTrocarTexturaDireita()) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : classJogador.getTrocarTexturaEsquerda()) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : classJogador.getTrocarTexturaCima()) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : classJogador.getTrocarTexturaBaixo()) {
            texturasMovimento.dispose();
        }
        batch.dispose();
    }

    public boolean isDireita() {
        return direita;
    }

    public void setDireita(boolean direita) {
        this.direita = direita;
    }

    public boolean isCima() {
        return cima;
    }

    public void setCima(boolean cima) {
        this.cima = cima;
    }

    public boolean isBaixo() {
        return baixo;
    }

    public void setBaixo(boolean baixo) {
        this.baixo = baixo;
    }

    public boolean isEsquerda() {
        return esquerda;
    }

    public void setEsquerda(boolean esquerda) {
        this.esquerda = esquerda;
    }
}
