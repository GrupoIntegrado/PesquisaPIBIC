package br.grupointegrado.Projeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;


/**
 * Created by Elito Fraga on 10/09/2015.
 */
public class TelaJogo extends TelaBase {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texturaBloco;
    private Texture texturaBloco2;
    private Texture texturaAgua;
    private Texture texturaFinal;
    private Texture texturaSplash;
    private Image telaAviso;
    private ImageTextButton btnNovoJogo;
    private ImageTextButton btnContinuar;
    private ImageTextButton btnVoltar;
    private Stage palcoMenu;
    private Label lbTituloAviso;
    private Label lbTexto;
    private BitmapFont fonteTexto;
    private BitmapFont fonteBotoes;
    private BitmapFont fonteTituloAviso;
    private Texture texturaBotao;
    private Texture texturaBotaoPressionado;
    private Texture texturaTelaAviso;
    private ImageTextButton btnSim;
    private ImageTextButton btnNao;
    private BitmapFont fonte;
    private Label lbContBlocos;
    private Label lbLevel;
    private Stage palcoInformacoes;
    private int CONT_BLOCO_REMOVIDO = 0;
    private float initX = 0;
    private float initY = 0;
    private Sprite splash;
    private float intervalo_frames = 0;
    private final float tempo_intervalo = 1 / 6f;
    private int estagio = 0;
    private boolean gameOver = false;
    private int cont_blocos_remover = 0;
    private Jogador classJogador;
    private Array<Array<Bloco>> caminho = new Array<Array<Bloco>>();
    private Array<Texture> trocarSplash = new Array<Texture>();

    public TelaJogo(MainJogo jogo) {
        super(jogo);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        palcoInformacoes = new Stage(new FillViewport(camera.viewportWidth, camera.viewportHeight, camera));
        palcoMenu = new Stage(new FillViewport(camera.viewportWidth, camera.viewportHeight, camera));

        Gdx.input.setInputProcessor(palcoMenu);
        if (jogo.isInicioJogo()) {
            Gdx.input.setInputProcessor(palcoInformacoes);
        }

        batch = new SpriteBatch();

        initTexturas();
        initNivel();
        initFonte();
        initLabels();
        initBotoes();
        initConfigJogador();
    }

    private void initBotoes() {

        ImageTextButton.ImageTextButtonStyle estilo = new ImageTextButton.ImageTextButtonStyle();
        estilo.font = fonteBotoes;
        estilo.up = new SpriteDrawable(new Sprite(texturaBotao));
        estilo.down = new SpriteDrawable(new Sprite(texturaBotaoPressionado));

        btnNovoJogo = new ImageTextButton(" Novo Jogo ", estilo);

        btnContinuar = new ImageTextButton("  Continuar  ", estilo);

        btnVoltar = new ImageTextButton("  Voltar  ", estilo);

        if (!jogo.isInicioJogo()) {
            palcoMenu.addActor(btnNovoJogo);
            palcoMenu.addActor(btnContinuar);
        }

        palcoInformacoes.addActor(btnVoltar);

        btnSim = new ImageTextButton("  Sim  ", estilo);

        btnNao = new ImageTextButton("  Nao  ", estilo);

        btnNovoJogo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
                int maiorLevel = pref.getInteger("MAIOR_LEVEL", 0);
                if (maiorLevel > 0) {
                    telaAviso = new Image(texturaTelaAviso);

                    float px = camera.viewportWidth / 2 - telaAviso.getWidth() / 2;
                    float py = camera.viewportHeight / 2 - telaAviso.getHeight() / 2;

                    telaAviso.setPosition(px, py);

                    palcoMenu.addActor(telaAviso);
                    palcoMenu.addActor(lbTituloAviso);
                    palcoMenu.addActor(lbTexto);
                    palcoMenu.addActor(btnSim);
                    palcoMenu.addActor(btnNao);

                    btnSim.addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
                            pref.putInteger("MAIOR_LEVEL", 0);
                            pref.flush();
                            jogo.setNivelAtual(0);
                            jogo.setInicioJogo(true);
                            reiniciarJogo();
                        }
                    });

                    btnNao.addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            telaAviso.remove();
                            lbTituloAviso.remove();
                            lbTexto.remove();
                            btnSim.remove();
                            btnNao.remove();
                        }
                    });

                }else {
                    jogo.setInicioJogo(true);
                    reiniciarJogo();
                }

            }
        });

        btnContinuar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
                int maiorLevel = pref.getInteger("MAIOR_LEVEL", 0);
                if (maiorLevel > 0) {
                    jogo.setNivelAtual(maiorLevel);
                    jogo.setInicioJogo(true);
                    reiniciarJogo();
                }
                System.out.println(maiorLevel);
            }
        });

        btnVoltar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                palcoMenu.addActor(btnNovoJogo);
                palcoMenu.addActor(btnContinuar);
                jogo.setInicioJogo(false);
                reiniciarJogo();
            }
        });
    }

    private void atualizarBotoes() {
        float x = camera.viewportWidth / 2 - btnNovoJogo.getPrefWidth() / 2;
        float y = camera.viewportHeight / 2 - btnNovoJogo.getPrefWidth() + 200;

        btnNovoJogo.setPosition(x, y);

        x = camera.viewportWidth / 2 - btnContinuar.getPrefWidth() / 2;
        y = camera.viewportHeight / 2 - btnContinuar.getPrefWidth() + 140;

        btnContinuar.setPosition(x, y);

        x = camera.viewportWidth / 2 - btnSim.getPrefWidth() - 20;
        y = camera.viewportHeight / 2 - btnSim.getPrefWidth();

        btnSim.setPosition(x, y);

        x = camera.viewportWidth / 2 - btnNao.getPrefWidth() + 110;
        y = camera.viewportHeight / 2 - btnNao.getPrefWidth() + 3;

        btnNao.setPosition(x, y);

        x = camera.viewportWidth / 2 - btnVoltar.getPrefWidth() + 490;
        y = camera.viewportHeight / 2 - btnVoltar.getPrefWidth() - 120;

        btnVoltar.setPosition(x, y);
    }


    private void atualizarLabels() {
        float x;
        float y;

        x = camera.viewportWidth / 2 - lbTituloAviso.getPrefWidth() / 2;
        y = camera.viewportHeight - 240;

        lbTituloAviso.setPosition(x, y);

        x = camera.viewportWidth / 2 - 135;
        y = camera.viewportHeight - 300;

        lbTexto.setPosition(x, y);

    }


    private void initLabels() {
        Label.LabelStyle lbEstilo = new Label.LabelStyle();
        lbEstilo.font = fonte;
        lbEstilo.fontColor = Color.WHITE;

        lbContBlocos = new Label("Blocos Restantes", lbEstilo);
        palcoInformacoes.addActor(lbContBlocos);

        lbLevel = new Label("Level", lbEstilo);
        palcoInformacoes.addActor(lbLevel);

        lbEstilo.font = fonteTituloAviso;
        lbTituloAviso = new Label(" Aviso ", lbEstilo);

        lbEstilo.font = fonteTexto;
        lbTexto = new Label("   Caso a opcao escolhida seja 'Sim' voce \n " +
                "perdera o seu jogo salvo, deseja continuar ?", lbEstilo);

    }

    private void initFonte() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.color = Color.WHITE;
        param.size = 24;
        param.shadowOffsetX = 2;
        param.shadowOffsetY = 2;

        fonte = generator.generateFont(param);


        param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 32;
        param.color = Color.WHITE;

        fonteBotoes = generator.generateFont(param);

        param.size = 48;
        param.color = Color.BLACK;

        fonteTituloAviso = generator.generateFont(param);

        param.size = 14;
        fonteTexto = generator.generateFont(param);

        generator.dispose();
    }

    private void initConfigJogador() {
        float disAndarX = texturaBloco.getWidth();
        float disAndarY = texturaBloco.getHeight();

        float posInicialJX = initX + jogo.getNivelAtual().getPosicaoInicial().x * texturaBloco.getWidth() - texturaBloco.getWidth() / 2;
        float posInicialJY = initY + jogo.getNivelAtual().getPosicaoInicial().y * (texturaBloco.getHeight() - texturaBloco.getHeight() / 3);

        classJogador = new Jogador(posInicialJX, posInicialJY, disAndarX, disAndarY);
    }

    private void initTexturas() {

        texturaBloco = new Texture("Texturas/bloco.png");
        texturaBloco2 = new Texture("Texturas/bloco2.png");
        texturaAgua = new Texture("Texturas/agua.png");
        texturaFinal = new Texture("Texturas/final.png");

        texturaBotao = new Texture("Texturas/button.png");
        texturaBotaoPressionado = new Texture("Texturas/button-down.png");
        texturaTelaAviso = new Texture("Texturas/telaaviso.png");

        for (int i = 1; i <= 6; i++) {
            texturaSplash = new Texture("Texturas/splash-" + i + ".png");
            trocarSplash.add(texturaSplash);
        }

        splash = new Sprite(trocarSplash.get(0));

    }

    private void informacaoBlocos() {
        lbContBlocos.setPosition(camera.viewportWidth / 2 - 19, camera.viewportHeight - lbContBlocos.getHeight());
        String blocos_total = "" + cont_blocos_remover;
        lbContBlocos.setText(CONT_BLOCO_REMOVIDO + "/" + blocos_total);

        lbLevel.setPosition(camera.viewportWidth / 6, camera.viewportHeight - lbContBlocos.getHeight());
        level = jogo.getNivelAtualIndex() + 1;
        lbLevel.setText("LEVEL " + level);
    }

    private int level;
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        informacaoBlocos();
        renderizarLabirinto();

        if (!gameOver && jogo.isInicioJogo()) {
            classJogador.atualizar(delta, batch);
        }else if (gameOver) {
            atualizarSplashJogador(delta);
            if (estagio == 5) {
                reiniciarJogo();
            }
        }
        atualizarLabels();
        atualizarBotoes();

        if (jogo.isInicioJogo()) {
            capturarTeclas();

            palcoInformacoes.act(delta);
            palcoInformacoes.draw();
        }

        palcoMenu.act(delta);
        palcoMenu.draw();
    }

    private void salvarLevel() {
        Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
        pref.putInteger("MAIOR_LEVEL", jogo.getNivelAtualIndex());
        pref.flush();
    }

    private void reiniciarJogo() {
        jogo.setScreen(new TelaJogo(jogo));
    }

    private void atualizarPosicaoSplashJogador() {
        float posQuedaX = 0;
        float posQuedaY = 0;

        switch (classJogador.getDirecao()) {
            case ESQUERDA:
                posQuedaX = classJogador.getX() - classJogador.getWidth() / 3;
                posQuedaY = classJogador.getY();
                splash.setPosition(posQuedaX, posQuedaY);
                break;
            case DIREITA:
                posQuedaX = classJogador.getX() + classJogador.getWidth() / 2;
                posQuedaY = classJogador.getY();
                splash.setPosition(posQuedaX, posQuedaY);
                break;
            case CIMA:
                posQuedaX = classJogador.getX();
                posQuedaY = classJogador.getY();
                splash.setPosition(posQuedaX, posQuedaY);
                break;
            case BAIXO:
                posQuedaX = classJogador.getX();
                posQuedaY = classJogador.getY() - classJogador.getHeight() / 6;
                splash.setPosition(posQuedaX, posQuedaY);
                break;
        }

    }

    private void atualizarEstagioSplash(float delta) {
        if (intervalo_frames >= tempo_intervalo) {
            intervalo_frames = 0;
            estagio ++;
            if (estagio > 5)
                estagio = 0;
        } else {
            intervalo_frames = intervalo_frames + delta;
        }
    }

    private void atualizarSplashJogador(float delta) {
        atualizarEstagioSplash(delta);
        atualizarPosicaoSplashJogador();

        splash.setTexture(trocarSplash.get(estagio));

        batch.begin();
        splash.draw(batch);
        batch.end();
    }

    private void initNivel() {
        for (BlocoTipo[] linha : jogo.getNivelAtual().getBlocos()) {
            criarLabirinto(linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6], linha[7], linha[8], linha[9], linha[10], linha[11]);
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
            if (tipo.equals(BlocoTipo.BLOCO) || tipo.equals(BlocoTipo.BLOCO2)) {
                cont_blocos_remover += 1;
            }
        }
        caminho.add(linha);
    }

    private int xAtual = 0; private int yAtual = 0;
    private Texture textura;
    private Rectangle recJogador = new Rectangle();
    private Rectangle recBloco = new Rectangle();

    private void renderizarLabirinto() {
        recJogador.set(classJogador.getX(), classJogador.getY(), classJogador.getWidth() / 2,
                classJogador.getHeight() / 2);
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

                recBloco.set(initX + bloco.getPosicao().x * textura.getWidth(), initY + 32 + bloco.getPosicao().y * (textura.getHeight() - 32),
                        textura.getWidth(), textura.getHeight());


                if (recBloco.contains(recJogador)) {
                    xAtual = (int) bloco.getPosicao().x;
                    yAtual = (int) bloco.getPosicao().y;

                    if (bloco.getTipo().equals(BlocoTipo.AGUA)) {
                        gameOver = true;
                    }

                    if ((caminho.get(yAtual).get(xAtual).getTipo().equals(BlocoTipo.FINAL)) && (CONT_BLOCO_REMOVIDO == cont_blocos_remover)) {
                        if (classJogador.getDirecao().equals(Direcao.PARADO)) {
                            int proxNivel = jogo.getNivelAtualIndex() + 1;
                            if (proxNivel < jogo.getNiveis().size) {
                                jogo.setNivelAtual(proxNivel);
                                salvarLevel();
                                reiniciarJogo();
                            }
                        }
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
                        initY + bloco.getPosicao().y * (textura.getHeight() - 32));
                batch.end();
            }
        }
    }

    private int xAnterior = 1; private int yAnterior = 0;
    private void atualizarBloco(BlocoTipo tipo) {
        boolean removido = false;

        if (yAnterior < yAtual) {
            yAnterior = yAtual - 1;
            caminho.get(yAnterior).get(xAtual).setTipo(tipo);
            removido = true;
        } else if (yAnterior > yAtual) {
            yAnterior = yAtual + 1;
            caminho.get(yAnterior).get(xAtual).setTipo(tipo);
            removido = true;
        } else if (xAnterior < xAtual) {
            xAnterior = xAtual - 1;
            caminho.get(yAtual).get(xAnterior).setTipo(tipo);
            removido = true;
        } else if (xAnterior > xAtual) {
            xAnterior = xAtual + 1;
            caminho.get(yAtual).get(xAnterior).setTipo(tipo);
            removido = true;
        }

        xAnterior = xAtual;
        yAnterior = yAtual;

        if (tipo.equals(BlocoTipo.AGUA) && removido) {
            CONT_BLOCO_REMOVIDO += 1;
        }
    }

    public void capturarTeclas() {
        if (classJogador.getDirecao() == Direcao.PARADO) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                classJogador.setDirecao(Direcao.ESQUERDA);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                classJogador.setDirecao(Direcao.DIREITA);
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                classJogador.setDirecao(Direcao.CIMA);
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                classJogador.setDirecao(Direcao.BAIXO);
            }
        }
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
        classJogador.getTexturaJogador().dispose();
        texturaBloco.dispose();
        texturaBloco2.dispose();
        texturaAgua.dispose();
        fonteBotoes.dispose();
        fonteTituloAviso.dispose();
        texturaBotao.dispose();
        texturaTelaAviso.dispose();
        texturaBotaoPressionado.dispose();
        for (int i = 1; i <= 6; i++) {
            texturaSplash.dispose();
        }
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
        palcoInformacoes.dispose();
        batch.dispose();
    }
}
