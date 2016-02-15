package br.grupointegrado.Projeto;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Preferences;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.Image;
        import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
        import com.badlogic.gdx.scenes.scene2d.ui.Label;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
        import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
        import com.badlogic.gdx.utils.viewport.FillViewport;

/**
 * Created by Elito Fraga on 28/12/2015.
 */
public class TelaMenu extends TelaBase {

    private OrthographicCamera camera;
    private Stage palco;
    private Image telaAviso;
    private ImageTextButton btnNovoJogo;
    private ImageTextButton btnContinuar;
    private Label lbTitulo;
    private Label lbTituloAviso;
    private Label lbTexto;
    private BitmapFont fonteTexto;
    private BitmapFont fonteTitulo;
    private BitmapFont fonteBotoes;
    private BitmapFont fonteTituloAviso;
    private Texture texturaBotao;
    private Texture texturaBotaoPressionado;
    private Texture texturaTelaAviso;
    private ImageTextButton btnSim;
    private ImageTextButton btnNao;


    public TelaMenu(MainJogo jogo) {
        super(jogo);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        palco = new Stage(new FillViewport(camera.viewportWidth, camera.viewportHeight, camera));
        Gdx.input.setInputProcessor(palco);

        initFontes();
        initLabels();
        initBotoes();
        initAviso();
    }

    private void initAviso() {
        texturaTelaAviso = new Texture("Texturas/telaaviso.png");
    }


    private void initBotoes() {
        texturaBotao = new Texture("Texturas/button.png");
        texturaBotaoPressionado = new Texture("Texturas/button-down.png");

        ImageTextButton.ImageTextButtonStyle estilo = new ImageTextButton.ImageTextButtonStyle();
        estilo.font = fonteBotoes;
        estilo.up = new SpriteDrawable(new Sprite(texturaBotao));
        estilo.down = new SpriteDrawable(new Sprite(texturaBotaoPressionado));

        btnNovoJogo = new ImageTextButton(" Novo Jogo ", estilo);
        palco.addActor(btnNovoJogo);

        btnContinuar = new ImageTextButton("  Continuar  ", estilo);
        palco.addActor(btnContinuar);

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

                    palco.addActor(telaAviso);
                    palco.addActor(lbTituloAviso);
                    palco.addActor(lbTexto);
                    palco.addActor(btnSim);
                    palco.addActor(btnNao);

                    btnSim.addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
                            pref.putInteger("MAIOR_LEVEL", 0);
                            pref.flush();
                            jogo.setScreen(new TelaJogo(jogo));
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

                }else jogo.setScreen(new TelaJogo(jogo));
            }
        });

        btnContinuar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
                int maiorLevel = pref.getInteger("MAIOR_LEVEL", 0);
                if (maiorLevel > 0) {
                    jogo.setNivelAtual(maiorLevel);
                    jogo.setScreen(new TelaJogo(jogo));
                }
            }
        });
    }


    private void initFontes() {

        FreeTypeFontGenerator gerador = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 48;
        params.color =  Color.WHITE;
        params.shadowOffsetX = 2;
        params.shadowOffsetY = 2;
        params.shadowColor = Color.RED;


        fonteTitulo = gerador.generateFont(params);

        params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 32;
        params.color = Color.BLACK;

        fonteBotoes = gerador.generateFont(params);

        params.size = 48;
        fonteTituloAviso = gerador.generateFont(params);

        params.size = 14;
        fonteTexto = gerador.generateFont(params);

        gerador.dispose();

    }

    private void initLabels() {

        Label.LabelStyle estilo = new Label.LabelStyle();
        estilo.font = fonteTitulo;

        lbTitulo = new Label("Jogo dos Blocos", estilo);
        palco.addActor(lbTitulo);

        estilo.font = fonteTituloAviso;
        lbTituloAviso = new Label(" Aviso ", estilo);

        estilo.font = fonteTexto;
        lbTexto = new Label("   Caso a opcao escolhida seja 'Sim' voce \n " +
                "perdera o seu jogo salvo, deseja continuar ?", estilo);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        atualizarLabels();
        atualizarBotoes();

        palco.act(delta);
        palco.draw();

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
    }


    private void atualizarLabels() {
        float x = camera.viewportWidth / 2 - lbTitulo.getPrefWidth() / 2;
        float y = camera.viewportHeight - 100;

        lbTitulo.setPosition(x, y);

        x = camera.viewportWidth / 2 - lbTituloAviso.getPrefWidth() / 2;
        y = camera.viewportHeight - 240;

        lbTituloAviso.setPosition(x, y);

        x = camera.viewportWidth / 2 - 135;
        y = camera.viewportHeight - 300;

        lbTexto.setPosition(x, y);

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
        palco.dispose();
        fonteTitulo.dispose();
        fonteBotoes.dispose();
        fonteTituloAviso.dispose();
        texturaBotao.dispose();
        texturaTelaAviso.dispose();
        texturaBotaoPressionado.dispose();
    }
}
