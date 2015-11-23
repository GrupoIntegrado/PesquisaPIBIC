package br.grupointegrado.Projeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;



/**
 * Created by Elito Fraga on 10/09/2015.
 */
public class TelaJogo extends TelaBase {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Jogador jogador;
    private Texture texturaBloco;
    private Texture texturaBloco2;
    private Texture texturaAgua;
    private Texture texturaFinal;
    private Texture texturaSplash;
    private Sprite splash;
    private float intervalo_frames = 0;
    private final float tempo_intervalo = 1 / 6f;
    private int estagio = 0;
    private boolean esquerda = false;
    private boolean direita = false;
    private boolean  cima = false;
    private boolean baixo = false;
    private boolean gameOver = false;
    private Array<Array<Bloco>> caminho = new Array<Array<Bloco>>();
    private Array<Texture> trocarSplash = new Array<Texture>();
    int c = 0;

    public TelaJogo(MainJogo jogo) {
        super(jogo);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        jogador = new Jogador();

        jogador.texturas();
        initTexturas();
        initNivel();
    }

    private void initTexturas() {

        texturaBloco = new Texture("Texturas/bloco.png");
        texturaBloco2 = new Texture("Texturas/bloco2.png");
        texturaAgua = new Texture("Texturas/agua.png");
        texturaFinal = new Texture("Texturas/final.png");

        for (int i = 1; i <= 6; i++ ) {
            texturaSplash = new Texture("Texturas/splash-" + i + ".png");
            trocarSplash.add(texturaSplash);
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        capiturarTeclas();
        renderizarLabirinto();
        atualizarPosicaoQueda();
        if (!gameOver) {
            jogador.atualizarJogador(delta);
        }else splashDAgua(delta);

    }

    private void reiniciarJogo() {
        jogo.setScreen(new TelaJogo(jogo));
    }

    float posQuedaX = 0; float posQuedaY = 0;
    private void atualizarPosicaoQueda() {

        if (direita) {
            direita = false;
            posQuedaX = jogador.getJogador().getX() + jogador.getJogador().getWidth();
            posQuedaY =  jogador.getJogador().getY();
        }else if (esquerda) {
            esquerda = false;
            posQuedaX = jogador.getJogador().getX() - jogador.getJogador().getWidth();
            posQuedaY =  jogador.getJogador().getY();
        }else if (cima) {
            cima = false;
            posQuedaX = jogador.getJogador().getX();
            posQuedaY = jogador.getJogador().getY() + jogador.getJogador().getHeight() / 2;
        }else if (baixo) {
            baixo = false;
            posQuedaX = jogador.getJogador().getX();
            posQuedaY = jogador.getJogador().getY() - jogador.getJogador().getHeight() / 1.5f;
        }

    }


    private void splashDAgua(float delta) {

        if (intervalo_frames >= tempo_intervalo) {
            intervalo_frames = 0;
            estagio++;
            if (estagio > 6)
                estagio = 0;
        } else {
            intervalo_frames = intervalo_frames + delta;
        }

        splash = new Sprite(trocarSplash.get(estagio));

        batch.begin();
        batch.draw(splash, posQuedaX, posQuedaY);
        batch.end();

        if (estagio == 5) {
            reiniciarJogo();
        }
    }


    private void initNivel() {
//        criarLabirinto(BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);
//        criarLabirinto(BlocoTipo.AGUA, BlocoTipo.BLOCO2, BlocoTipo.BLOCO2, BlocoTipo.BLOCO, BlocoTipo.AGUA);
//        criarLabirinto(BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
//        criarLabirinto(BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.AGUA);
//        criarLabirinto(BlocoTipo.AGUA, BlocoTipo.BLOCO, BlocoTipo.BLOCO, BlocoTipo.FINAL, BlocoTipo.AGUA);
//        criarLabirinto(BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA, BlocoTipo.AGUA);

        for (BlocoTipo[] linha : jogo.getNivelAtual().getBlocos()) {
            criarLabirinto(linha[0], linha[1], linha[2], linha[3], linha[4]);
        }
    }

    public void criarLabirinto(BlocoTipo... tipos) {
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

    private float initX = 80; private float initY = 45;

    private int xAnterior = 1; private int yAnterior = 0;
    private int xAtual = 0; private int yAtual = 0;
    private Texture textura;
    private Rectangle recJogador = new Rectangle();
    private Rectangle recBloco = new Rectangle();
    private void renderizarLabirinto() {
        recJogador.set(jogador.getJogador().getX(), jogador.getJogador().getY(), jogador.getJogador().getWidth() / 2,
                jogador.getJogador().getHeight() / 2);
        for (int i = caminho.size - 1; i >= 0 ; i--) {
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

                recBloco.set(initX + bloco.getPosicao().x * (textura.getWidth() - 10), initY + 43 + bloco.getPosicao().y * (textura.getHeight() - 41),
                        textura.getWidth(), textura.getHeight());
                if (recBloco.contains(recJogador)) {
                    xAtual = (int) bloco.getPosicao().x;
                    yAtual = (int) bloco.getPosicao().y;

                    if (bloco.getTipo().equals(BlocoTipo.AGUA)) {
                        gameOver = true;
                    }

                    if (caminho.get(yAtual).get(xAtual).getTipo().equals(BlocoTipo.FINAL)) {
                        jogo.kk = 2;
                    }

                    if (caminho.get(yAnterior).get(xAnterior).getTipo().equals(BlocoTipo.BLOCO2)) {
                        atualizarBloco(BlocoTipo.BLOCO);
                    } else if (caminho.get(yAnterior).get(xAnterior).getTipo().equals(BlocoTipo.BLOCO)) {
                        atualizarBloco(BlocoTipo.AGUA);
                    } else {
                        xAnterior = xAtual;
                        yAnterior = yAtual;
                    }

                }

                batch.begin();
                batch.draw(textura, initX + bloco.getPosicao().x * textura.getWidth(),
                        initY + bloco.getPosicao().y * (textura.getHeight() - 42));
                batch.end();
            }
        }
    }

    private void atualizarBloco(BlocoTipo tipo) {
        if (yAnterior < yAtual) {
            yAnterior = yAtual - 1;
            caminho.get(yAnterior).get(xAtual).setTipo(tipo);
            yAnterior = yAnterior + 1;
            xAnterior = xAtual;
        } else if (yAnterior > yAtual) {
            yAnterior = yAtual + 1;
            caminho.get(yAnterior).get(xAtual).setTipo(tipo);
            yAnterior = yAnterior - 1;
            xAnterior = xAtual;
        } else if (xAnterior < xAtual) {
            xAnterior = xAtual - 1;
            caminho.get(yAtual).get(xAnterior).setTipo(tipo);
            xAnterior = xAtual;
        } else if (xAnterior > xAtual) {
            caminho.get(yAtual).get(xAnterior).setTipo(tipo);
            xAnterior = xAtual;
        }
    }

    private boolean direcao = false;
    public boolean capiturarTeclas() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            esquerda = true;
            direcao = esquerda;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direita = true;
            esquerda = false;
            direcao = direita;
        }else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cima = true;
            direcao = cima;
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            baixo = true;
            direcao = baixo;
        }
        return  direcao;
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
    public void dispose() {
        jogador.getTexturaJogador().dispose();
        texturaBloco.dispose();
        texturaBloco2.dispose();
        texturaAgua.dispose();
        for (int i = 1; i <= 6; i++) {
            texturaSplash.dispose();
        }
        for (Texture texturasMovimento : jogador.getTrocarTexturaDireita()) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : jogador.getTrocarTexturaEsquerda()) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : jogador.getTrocarTexturaCima()) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : jogador.getTrocarTexturaBaixo()) {
            texturasMovimento.dispose();
        }
        batch.dispose();
    }


    public boolean isEsquerda() {
        return esquerda;
    }

    public void setEsquerda(boolean esquerda) {
        this.esquerda = esquerda;
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
}
