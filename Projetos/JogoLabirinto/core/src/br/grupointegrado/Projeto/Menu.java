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

/**
 * Created by Elito Fraga on 27/03/2016.
 */
public class Menu extends TelaBase{

    private OrthographicCamera cameraMenu;
    private Stage palcoMenu;
    private Image telaAviso;
    private ImageTextButton btnNovoJogo;
    private Label lbTituloAviso;
    private Label lbTexto;
    private BitmapFont fonteTexto;
    private BitmapFont fonteBotoes;
    private BitmapFont fonteTituloAviso;
    private ImageTextButton btnContinuar;
    private ImageTextButton btnSim;
    private ImageTextButton btnNao;
    private Texture texturaBotao;
    private Texture texturaBotaoPressionado;
    private Texture texturaTelaAviso;
    private boolean nv = false;
    private int maiorLevel = 0;

    public Menu(MainJogo jogo) {
        super(jogo);
    }

    private void initTexturas () {
        texturaBotao = new Texture("Texturas/button.png");
        texturaBotaoPressionado = new Texture("Texturas/button-down.png");
        texturaTelaAviso = new Texture("Texturas/telaaviso.png");

        telaAviso = new Image(texturaTelaAviso);
    }

    private void initLabelsBotoes() {
        ImageTextButton.ImageTextButtonStyle estilo = new ImageTextButton.ImageTextButtonStyle();
        estilo.font = fonteBotoes;
        estilo.up = new SpriteDrawable(new Sprite(texturaBotao));
        estilo.down = new SpriteDrawable(new Sprite(texturaBotaoPressionado));

        btnNovoJogo = new ImageTextButton(" Novo Jogo ", estilo);

        btnContinuar = new ImageTextButton("  Continuar  ", estilo);

        btnSim = new ImageTextButton("  Sim  ", estilo);

        btnNao = new ImageTextButton("  Nao  ", estilo);
    }

    private void initFonteBotoes() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

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

    private void initLabelsAviso() {
        Label.LabelStyle lbEstilo = new Label.LabelStyle();
        lbEstilo.fontColor = Color.WHITE;

        lbEstilo.font = fonteTituloAviso;
        lbTituloAviso = new Label(" Aviso ", lbEstilo);

        lbEstilo.font = fonteTexto;
        lbTexto = new Label("   Caso a opcao escolhida seja 'Sim' voce \n " +
                "perdera o seu jogo salvo, deseja continuar ?", lbEstilo);

    }

    private void atualizarAviso() {
        float px = cameraMenu.viewportWidth / 2 - telaAviso.getWidth() / 2;
        float py = cameraMenu.viewportHeight / 2 - telaAviso.getHeight() / 2;
        telaAviso.setPosition(px, py);
    }

    private void atualizarBotoes() {

        btnNovoJogo.setPosition(cameraMenu.viewportWidth / 2 - btnNovoJogo.getPrefWidth() / 2,
                cameraMenu.viewportHeight / 2 - btnNovoJogo.getPrefHeight() + 70);

        btnContinuar.setPosition(cameraMenu.viewportWidth / 2 - btnContinuar.getPrefWidth() / 2,
                cameraMenu.viewportHeight / 2 - btnContinuar.getPrefHeight() + 20);

        btnSim.setPosition(cameraMenu.viewportWidth / 2 - btnSim.getPrefWidth() - 20,
                cameraMenu.viewportHeight / 2 - btnSim.getPrefHeight() - 40);

        btnNao.setPosition(cameraMenu.viewportWidth / 2 - btnNao.getPrefWidth() + 110,
                cameraMenu.viewportHeight / 2 - btnNao.getPrefHeight() - 40);

    }

    private void atualizarLabelAviso() {
        lbTituloAviso.setPosition(cameraMenu.viewportWidth / 2 - lbTituloAviso.getPrefWidth() / 2,
                cameraMenu.viewportHeight - 240);
        lbTexto.setPosition(cameraMenu.viewportWidth / 2 - 135, cameraMenu.viewportHeight - 300);
    }

    private void atualizaMenu() {

        btnNovoJogo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (nv) {

                    palcoMenu.addActor(telaAviso);
                    palcoMenu.addActor(lbTituloAviso);
                    palcoMenu.addActor(lbTexto);
                    palcoMenu.addActor(btnSim);
                    palcoMenu.addActor(btnNao);

                    telaAviso.setVisible(true);
                    lbTituloAviso.setVisible(true);
                    lbTexto.setVisible(true);
                    btnSim.setVisible(true);
                    btnNao.setVisible(true);

                    btnSim.addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
                            pref.putInteger("MAIOR_LEVEL", -1);
                            pref.flush();
                            jogo.setNivelAtual(0);
                            jogo.setScreen(new TelaJogo(jogo));
                        }
                    });

                    btnNao.addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            telaAviso.setVisible(false);
                            lbTituloAviso.setVisible(false);
                            lbTexto.setVisible(false);
                            btnSim.setVisible(false);
                            btnNao.setVisible(false);
                            btnNovoJogo.setVisible(true);
                            btnContinuar.setVisible(true);
                        }
                    });
                }else jogo.setScreen(new TelaJogo(jogo));
            }
        });

        btnContinuar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (nv) {
                    jogo.setNivelAtual(maiorLevel + 1);
                    jogo.setScreen(new TelaJogo(jogo));
                }
            }
        });
    }

    @Override
    public void show() {
        cameraMenu = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        palcoMenu = new Stage();

        Gdx.input.setInputProcessor(palcoMenu);
        initTexturas();
        initFonteBotoes();
        initLabelsBotoes();
        initLabelsAviso();

        Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
        maiorLevel = pref.getInteger("MAIOR_LEVEL", 0);
        if (maiorLevel >= 0) {
            nv = true;
        }

        palcoMenu.addActor(btnNovoJogo);
        if (nv) {
            palcoMenu.addActor(btnContinuar);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        atualizarBotoes();
        atualizarLabelAviso();
        atualizarAviso();
        atualizaMenu();

        palcoMenu.act();
        palcoMenu.draw();
    }

    @Override
    public void resize(int width, int height) {
        palcoMenu.getViewport().update(width, height);
        cameraMenu.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        texturaBotao.dispose();
        texturaBotaoPressionado.dispose();
        texturaTelaAviso.dispose();
        palcoMenu.dispose();
    }
}
