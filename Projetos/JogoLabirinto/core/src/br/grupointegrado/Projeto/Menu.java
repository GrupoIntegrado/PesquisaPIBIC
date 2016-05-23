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

/**
 * Created by Elito Fraga on 27/03/2016.
 */
public class Menu extends TelaBase{

    private OrthographicCamera cameraMenu;
    private Stage palcoMenu;
    private Label lbListaNivel;
    private BitmapFont fonteBotoes;
    private ImageTextButton btnProximo;
    private ImageTextButton btnAnterior;
    private ImageTextButton btnLista;
    private ImageTextButton btnJogar;
    private ImageTextButton btnCreditos;
    private ImageTextButton btnSair;
    private Texture texturaBotao;
    private Texture texturaBotaoPressionado;
    private static boolean jogar = false;
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

        btnProximo = new ImageTextButton(" > ", estilo);

        btnSair = new ImageTextButton(" Sair ", estilo);

        btnAnterior = new ImageTextButton(" < ", estilo);

        btnJogar = new ImageTextButton(" Jogar ", estilo);

        btnCreditos = new ImageTextButton(" Créditos ", estilo);
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
                 cameraMenu.viewportHeight / 2 - btnJogar.getPrefWidth() + 200);

        btnCreditos.setPosition(cameraMenu.viewportWidth / 2 - btnCreditos.getPrefWidth() / 2,
                cameraMenu.viewportHeight / 2 - btnCreditos.getPrefWidth() + 190);

        btnProximo.setPosition(cameraMenu.viewportWidth - btnProximo.getPrefWidth() / 2 - 200,
                cameraMenu.viewportHeight / 2 - btnProximo.getPrefHeight() - 200);

        btnAnterior.setPosition(cameraMenu.viewportWidth - btnAnterior.getPrefWidth() / 2 - 250,
                cameraMenu.viewportHeight / 2 - btnAnterior.getPrefHeight() - 200);

        btnSair.setPosition(cameraMenu.viewportWidth / 2 - btnSair.getPrefWidth() + 450,
                cameraMenu.viewportHeight / 2 - btnSair.getPrefHeight() - 200);

    }

    private int contListagem = 0;
    private static int proximo = 0;
    private static int anterior = 0;
    private void acaoBotoes() {
        btnJogar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnJogar.remove();
                btnCreditos.remove();
                jogar = true;
            }
        });

        btnCreditos.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnJogar.setVisible(false);
                btnCreditos.setVisible(false);
            }
        });

        btnProximo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (proximo < jogo.getNiveis().size - 1) {
                    proximo += 3;
                    jogo.setNivelAtual(proximo);
                    jogo.setScreen(new Menu(jogo));
                }else {
                    proximo = 0;
                    jogo.setNivelAtual(proximo);
                    jogo.setScreen(new Menu(jogo));
                }
            }
        });

        btnAnterior.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                anterior = proximo;
                if (anterior > 0) {
                    anterior += -3;
                    proximo += - 3;
                    jogo.setNivelAtual(anterior);
                    jogo.setScreen(new Menu(jogo));
                }else if (anterior == 0){
                    anterior = jogo.getNiveis().size - 1;
                    proximo = anterior;
                    jogo.setNivelAtual(anterior);
                    jogo.setScreen(new Menu(jogo));
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

        btnLista = new ImageTextButton(" Selecionar  ", estilo);

        btnLista.setPosition(cameraMenu.viewportWidth / 1.6f - btnLista.getPrefWidth() / 2, cameraMenu.viewportHeight / 2 + 250 - yl);

        if (cd == 0) {
            palcoMenu.addActor(btnLista);
        }else if (cd > 0) {
            int nv_anterior = cd - 1;
            Preferences prefNivel = Gdx.app.getPreferences("NIVEL" + nv_anterior);
            String codigo_salvo = prefNivel.getString("CODIGO_SUCESSO");
            if (!codigo_salvo.isEmpty()) {
                palcoMenu.addActor(btnLista);
            }
        }

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

        if (!jogar) {
            palcoMenu.addActor(btnJogar);
            palcoMenu.addActor(btnCreditos);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        atualizarBotoes();
        acaoBotoes();

        if (jogar) {
            while (jogo.getNivelAtualIndex() < jogo.getNiveis().size && contListagem <= 2) {
                listaNivel(jogo.getNivelAtualIndex());
                jogo.setNivelAtual(jogo.getNivelAtualIndex() + 1);
                contListagem += 1;
            }
            palcoMenu.addActor(btnProximo);
            palcoMenu.addActor(btnAnterior);
            palcoMenu.addActor(btnSair);
        }

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