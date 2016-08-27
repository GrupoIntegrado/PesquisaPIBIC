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
import com.sun.javafx.property.adapter.PropertyDescriptor;

/**
 * Created by Elito Fraga on 27/03/2016.
 */
public class Menu extends TelaBase{

    private OrthographicCamera cameraMenu;
    private Stage palcoMenu;
    private Label lbListaNivel;
    private BitmapFont fonteBotoes;
    private ImageTextButton btnJogar;
    private ImageTextButton btnLista;
    private ImageTextButton btnSair;
    private Texture texturaBotao;
    private Texture texturaBotaoPressionado;
    private boolean jogoSalvo = false;
    private int cd_nv = 0;
    private float yl = 0;
    private ImageTextButton.ImageTextButtonStyle estilo = new ImageTextButton.ImageTextButtonStyle();


    public Menu(MainJogo jogo) {
        super(jogo);
    }

    private void initTexturas () {
        texturaBotao = new Texture("Texturas/button.png");
        texturaBotaoPressionado = new Texture("Texturas/button-down.png");
    }

    private void initLabelsBotoes() {
        estilo.font = fonteBotoes;
        estilo.up = new SpriteDrawable(new Sprite(texturaBotao));
        estilo.down = new SpriteDrawable(new Sprite(texturaBotaoPressionado));

        btnJogar = new ImageTextButton(" JOGAR ", estilo);

        btnSair = new ImageTextButton(" Sair ", estilo);
    }

    private void initFonteBotoes() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.size = 32;
        param.color = Color.WHITE;
        fonteBotoes = generator.generateFont(param);

    }

    private void atualizarBotoes() {

        btnJogar.setPosition(cameraMenu.viewportWidth / 2 - btnJogar.getPrefWidth() / 2,
                cameraMenu.viewportHeight / 2 - btnJogar.getPrefHeight() + 70);

        btnSair.setPosition(cameraMenu.viewportWidth / 2 - btnSair.getPrefWidth() + 450,
                cameraMenu.viewportHeight / 2 - btnSair.getPrefHeight() - 200);

    }

    private void acaoBotoes() {

        btnJogar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnJogar.remove();
                palcoMenu.addActor(btnSair);

                while (cd_nv < jogo.getNiveis().size) {
                    listaNivel(cd_nv);
                    cd_nv += 1;
                }
            }
        });

        btnSair.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    public void listaNivel(final int cd) {
        Label.LabelStyle lbEstilo = new Label.LabelStyle();
        lbEstilo.font = fonteBotoes;
        lbEstilo.fontColor = Color.WHITE;

        int nv_text = cd + 1;
        lbListaNivel = new Label("NIVEL " + nv_text, lbEstilo);
        palcoMenu.addActor(lbListaNivel);

        yl += 50;

        lbListaNivel.setPosition(cameraMenu.viewportWidth / 3 - lbListaNivel.getPrefWidth() / 2, cameraMenu.viewportHeight / 2 + 250 - yl);

        btnLista = new ImageTextButton(" JOGAR  ", estilo);

        btnLista.setPosition(cameraMenu.viewportWidth / 1.6f - btnLista.getPrefWidth() / 2, cameraMenu.viewportHeight / 2 + 250 - yl);

        palcoMenu.addActor(btnLista);

        btnLista.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                jogo.setNivelAtual(cd);
                jogo.setScreen(new TelaJogo(jogo));
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

        jogoSalvo = false;

        palcoMenu.addActor(btnJogar);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        atualizarBotoes();
        acaoBotoes();

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
        palcoMenu.dispose();
    }
}