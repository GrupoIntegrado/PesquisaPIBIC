package br.grupointegrado.Projeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by Elito Fraga on 10/09/2015.
 */
public class TelaJogo extends TelaBase {
    private OrthographicCamera cameraInformacoes;
    private SpriteBatch pincel;
    private Stage palcoInformacoes;
    private Jogador jogador;
    private BitmapFont fonteBotoes;
    private int cont_blocos_remover = 0;
    private ImageTextButton btnVoltar;
    private ImageTextButton btnCompilar;
    private ImageTextButton btnExecutar;
    private ImageTextButton btnCancelar;
    private ImageTextButton btnAjuda;
    private Texture texturaBtnExecutar;
    private Texture texturaBtnExecutar2;
    private Texture texturaBtnCancelar;
    private Texture texturaBtnCancelar2;
    private Texture texturabtnCompilar;
    private Texture texturabtnCompilar2;
    private Texture texturabtnVoltar;
    private Texture texturabtnVoltar2;
    private Texture texturabtnAjuda;
    private Texture texturabtnAjuda2;
    private Texture texturabtnProximo;
    private Texture texturabtnProximo2;
    private Texture texturabtnAnterior;
    private Texture texturabtnAnterior2;
    private Texture texturabtnOK;
    private Texture texturabtnOK2;
    private ImageTextButton btnProximo;
    private ImageTextButton btnAnterior;
    private ImageTextButton btnOk;

    private Tabuleiro tabuleiro = new Tabuleiro();
    private Splash splash = new Splash();

    public TelaJogo(MainJogo jogo) {
        super(jogo);
    }

    private void initCamera() {
        cameraInformacoes = new OrthographicCamera(MainJogo.WIDTH, MainJogo.HEIGHT);
        cameraInformacoes.setToOrtho(false, MainJogo.WIDTH, MainJogo.HEIGHT);
        cameraInformacoes.update();
    }

    private void initJogador() {
        float posInicialJX = (tabuleiro.getInitX() + (tabuleiro.getSpriteBloco().getWidth() * jogo.getNivelAtual().getPosicaoInicial().x)
                - tabuleiro.getSpriteBloco().getWidth() / 2);
        float posInicialJY = (tabuleiro.getInitY() + 20 + (((tabuleiro.getSpriteBloco().getHeight() - 20) * jogo.getNivelAtual().getPosicaoInicial().y)
                - ((tabuleiro.getSpriteBloco().getHeight() - 20) / 2)));

        float disAndarX = tabuleiro.getSpriteBloco().getWidth();
        float disAndarY = tabuleiro.getSpriteBloco().getHeight() - 20;

        jogador = new Jogador(posInicialJX, posInicialJY, disAndarX, disAndarY);
    }

    private void desenhar(float delta) {
        pincel.begin();
        pincel.setProjectionMatrix(cameraInformacoes.combined);
        tabuleiro.desenhar(pincel, jogador);
        jogador.desenhar(delta, pincel);
        if (tabuleiro.atualizaTipoBloco()) {
            splash.atualizarPosicaoSplashBloco(jogador, tabuleiro.getSpriteBloco().getWidth(), tabuleiro.getSpriteBloco().getHeight(),
                    tabuleiro.xAnterior, tabuleiro.xAtual, tabuleiro.yAnterior, tabuleiro.yAtual, tabuleiro.getInitX(), tabuleiro.getInitY());
            //splash.desenharSplashBloco(delta, pincel);
        }
        //splash.desenharSplashJogador(delta, pincel, jogador);
        pincel.end();
    }

    private void initFontes() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.shadowOffsetX = 2;
        param.shadowOffsetY = 2;

        param.size = 16;
        fonteBotoes = generator.generateFont(param);
    }

    private void initBotoes() {
        initBotaoCompilar();
        initBotaoCancelar();
        initBotaoVoltar();
        initBotaoExecutar();
        initBotaoSetaE();
        initBotaoSetaD();
        initBotaoOK();
        initBotaoAjuda();
    }

    private void atualizarBotoes() {
        btnVoltar.setPosition(palcoInformacoes.getWidth() / 2 - btnVoltar.getPrefWidth() / 2 + btnVoltar.getPrefWidth(),
                palcoInformacoes.getHeight() / 25);

        btnCompilar.setPosition((palcoInformacoes.getWidth()/ 2 - btnCompilar.getPrefWidth() / 2 - 5),
                palcoInformacoes.getHeight() / 25);

        btnAjuda.setPosition(palcoInformacoes.getWidth() + btnAjuda.getPrefWidth(),
                palcoInformacoes.getHeight() / 25);
    }

    private void funcaoBotes() {

    }

    private void initBotaoExecutar(){
        texturaBtnExecutar = new Texture("Texturas/jogar.png");
        texturaBtnExecutar2 = new Texture("Texturas/jogar2.png");

        ImageTextButton.ImageTextButtonStyle estExecutar = new ImageTextButton.ImageTextButtonStyle();
        estExecutar.font = fonteBotoes;
        estExecutar.up = new SpriteDrawable(new Sprite(texturaBtnExecutar));
        estExecutar.down = new SpriteDrawable(new Sprite(texturaBtnExecutar2));

        btnExecutar = new ImageTextButton("", estExecutar);
    }

    private void initBotaoCancelar(){
        texturaBtnCancelar = new Texture("Texturas/sair.png");
        texturaBtnCancelar2 = new Texture("Texturas/sair2.png");

        ImageTextButton.ImageTextButtonStyle estCancelar = new ImageTextButton.ImageTextButtonStyle();
        estCancelar.font = fonteBotoes;
        estCancelar.up = new SpriteDrawable(new Sprite(texturaBtnCancelar));
        estCancelar.down = new SpriteDrawable(new Sprite(texturaBtnCancelar2));

        btnCancelar = new ImageTextButton("", estCancelar);
    }

    private void initBotaoCompilar(){
        texturabtnCompilar = new Texture("Texturas/compilar.png");
        texturabtnCompilar2 = new Texture("Texturas/compilar2.png");

        ImageTextButton.ImageTextButtonStyle estCompilar = new ImageTextButton.ImageTextButtonStyle();
        estCompilar.font = fonteBotoes;
        estCompilar.up = new SpriteDrawable(new Sprite(texturabtnCompilar));
        estCompilar.down = new SpriteDrawable(new Sprite(texturabtnCompilar2));


        btnCompilar = new ImageTextButton("", estCompilar);
    }

    private void initBotaoVoltar(){
        texturabtnVoltar = new Texture("Texturas/voltar.png");
        texturabtnVoltar2 = new Texture("Texturas/voltar2.png");

        ImageTextButton.ImageTextButtonStyle estVoltar = new ImageTextButton.ImageTextButtonStyle();
        estVoltar.font = fonteBotoes;
        estVoltar.up = new SpriteDrawable(new Sprite(texturabtnVoltar));
        estVoltar.down = new SpriteDrawable(new Sprite(texturabtnVoltar2));


        btnVoltar = new ImageTextButton("", estVoltar);
    }

    private void initBotaoAjuda(){
        texturabtnAjuda = new Texture("Texturas/ajuda.png");
        texturabtnAjuda2 = new Texture("Texturas/ajuda2.png");

        ImageTextButton.ImageTextButtonStyle estAjuda = new ImageTextButton.ImageTextButtonStyle();
        estAjuda.font = fonteBotoes;
        estAjuda.up = new SpriteDrawable(new Sprite(texturabtnAjuda));
        estAjuda.down = new SpriteDrawable(new Sprite(texturabtnAjuda2));


        btnAjuda = new ImageTextButton("", estAjuda);
    }

    private void initBotaoOK(){
        texturabtnOK = new Texture("Texturas/ok.png");
        texturabtnOK2 = new Texture("Texturas/ok2.png");

        ImageTextButton.ImageTextButtonStyle estOK = new ImageTextButton.ImageTextButtonStyle();
        estOK.font = fonteBotoes;
        estOK.up = new SpriteDrawable(new Sprite(texturabtnOK));
        estOK.down = new SpriteDrawable(new Sprite(texturabtnOK2));


        btnOk = new ImageTextButton("", estOK);
    }

    private void initBotaoSetaD(){
        texturabtnProximo = new Texture("Texturas/setad.png");
        texturabtnProximo2 = new Texture("Texturas/setad2.png");

        ImageTextButton.ImageTextButtonStyle estD = new ImageTextButton.ImageTextButtonStyle();
        estD.font = fonteBotoes;
        estD.up = new SpriteDrawable(new Sprite(texturabtnProximo));
        estD.down = new SpriteDrawable(new Sprite(texturabtnProximo2));


        btnProximo = new ImageTextButton("", estD);
    }

    private void initBotaoSetaE(){
        texturabtnAnterior = new Texture("Texturas/setae.png");
        texturabtnAnterior2 = new Texture("Texturas/setae2.png");

        ImageTextButton.ImageTextButtonStyle estE = new ImageTextButton.ImageTextButtonStyle();
        estE.font = fonteBotoes;
        estE.up = new SpriteDrawable(new Sprite(texturabtnAnterior));
        estE.down = new SpriteDrawable(new Sprite(texturabtnAnterior2));


        btnAnterior = new ImageTextButton("", estE);
    }

    @Override
    public void show() {
        initCamera();
        palcoInformacoes = new Stage(new StretchViewport(cameraInformacoes.viewportWidth, cameraInformacoes.viewportHeight));
        pincel = new SpriteBatch();

        tabuleiro.initTextura();
        tabuleiro.redimencionaTextura();
        tabuleiro.initNivel(jogo);

        splash.initTextura();

        initJogador();
        initFontes();
        initBotoes();

        System.out.println("reiniciou");

        palcoInformacoes.addActor(btnCompilar);
        palcoInformacoes.addActor(btnVoltar);
        palcoInformacoes.addActor(btnAjuda);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        capturarTeclas(jogador);
        atualizarBotoes();

        desenhar(delta);

        palcoInformacoes.act();
        palcoInformacoes.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            jogo.setScreen(new TelaJogo(jogo));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        pincel.dispose();
        palcoInformacoes.dispose();
        texturabtnAjuda.dispose();
        texturabtnAjuda2.dispose();
        texturabtnAnterior.dispose();
        texturabtnAnterior2.dispose();
        texturaBtnCancelar.dispose();
        texturaBtnCancelar2.dispose();
        texturabtnOK.dispose();
        texturabtnOK2.dispose();
        texturabtnProximo.dispose();
        texturabtnProximo2.dispose();
        texturabtnCompilar.dispose();
        texturabtnCompilar2.dispose();
        texturabtnVoltar.dispose();
        texturabtnVoltar2.dispose();
        fonteBotoes.dispose();
        tabuleiro.disposeTabuleiro();
        jogador.disposeJogador();
        splash.disposeSplash();
    }

    public void capturarTeclas(Jogador jogador) {
        if (jogador.getDirecao() == Direcao.PARADO) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                jogador.setDirecao(Direcao.ESQUERDA);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                jogador.setDirecao(Direcao.DIREITA);
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                jogador.setDirecao(Direcao.CIMA);
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                jogador.setDirecao(Direcao.BAIXO);
            }
        }
    }
}
