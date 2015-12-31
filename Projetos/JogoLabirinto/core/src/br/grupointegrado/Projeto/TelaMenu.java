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
    private ImageTextButton btnNovoJogo;
    private ImageTextButton btnContinuar;
    private Label lbTitulo;
    private BitmapFont fonteTitulo;
    private BitmapFont fonteBotoes;
    private Texture texturaBotao;
    private Texture texturaBotaoPressionado;


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

        btnNovoJogo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
                pref.putInteger("MAIOR_LEVEL", 0);
                pref.flush();
                jogo.setScreen(new TelaJogo(jogo));
            }
        });

        btnContinuar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences preferencias = Gdx.app.getPreferences("JOGOBLOCOS");
                int maiorLevel = preferencias.getInteger("MAIOR_LEVEL", 0);
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
        params.color = new Color(.25f, .25f, .85f, 1);
        params.shadowOffsetX = 2;
        params.shadowOffsetY = 2;
        params.shadowColor = Color.BLACK;


        fonteTitulo = gerador.generateFont(params);

        params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 32;
        params.color = Color.BLACK;

        fonteBotoes = gerador.generateFont(params);

        gerador.dispose();

    }

    private void initLabels() {

        Label.LabelStyle estilo = new Label.LabelStyle();
        estilo.font = fonteTitulo;

        lbTitulo = new Label("Jogo dos Blocos", estilo);
        palco.addActor(lbTitulo);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
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
    }

    private void atualizarLabels() {
        float x = camera.viewportWidth / 2 - lbTitulo.getPrefWidth() / 2;
        float y = camera.viewportHeight - 100;

        lbTitulo.setPosition(x, y);

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
        texturaBotao.dispose();
        texturaBotaoPressionado.dispose();
    }
}
