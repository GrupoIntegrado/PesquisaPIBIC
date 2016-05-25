package br.grupointegrado.Projeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private Label lbapoio;
    private Label lbrealizacao;
    private BitmapFont fonteBotoes;
    private BitmapFont fonte;
    private BitmapFont fontelogo;
    private ImageTextButton btnProximo;
    private ImageTextButton btnAnterior;
    private ImageTextButton btnLista;
    private ImageTextButton btnJogar;
    private ImageTextButton btnCreditos;
    private ImageTextButton btnSair;
    private Texture texturaBotao;
    private Texture texturaBotaoPressionado;
    private Texture texturaLogoIntegrado;
    private Texture texturaLogoCnpq;
    private SpriteBatch pincel;
    private Sprite spriteintegrado;
    private Sprite spritecnpq;
    private static boolean jogar = false;
    private float yl = 0;
    private ImageTextButton.ImageTextButtonStyle estilo = new ImageTextButton.ImageTextButtonStyle();


    public Menu(MainJogo jogo) {
        super(jogo);
    }

    private void initTexturas () {
        texturaBotao = new Texture("Texturas/button.png");
        texturaBotaoPressionado = new Texture("Texturas/button-down.png");
        texturaLogoIntegrado = new Texture("Texturas/logointegrado.png");
        texturaLogoCnpq = new Texture("Texturas/logocnpq.png");
    }

    private void initLogos() {
        pincel = new SpriteBatch();

        spriteintegrado = new Sprite(texturaLogoIntegrado);
        spriteintegrado.setSize(300,226);
        spriteintegrado.setPosition(80, 30);

        spritecnpq = new Sprite(texturaLogoCnpq);
        spritecnpq.setSize(440, 132);
        spritecnpq.setPosition(530, 60);
    }

    private void atualizaLogos() {
        pincel.begin();
        spriteintegrado.draw(pincel);
        spritecnpq.draw(pincel);
        pincel.end();
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

        param.color = Color.BLUE;
        fonte = generator.generateFont(param);

        param.size = 18;
        fontelogo = generator.generateFont(param);

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

    private Label.LabelStyle lbEstilo = new Label.LabelStyle();
    private void initLabel() {
        lbEstilo.font = fonte;
        lbEstilo.fontColor = Color.WHITE;

    }

    private void initLabelLogos() {
        Label.LabelStyle lblogo = new Label.LabelStyle();

        lblogo.font = fontelogo;
        lblogo.fontColor = Color.BLUE;

        lbapoio = new Label("Apoio:", lblogo);
        palcoMenu.addActor(lbapoio);

        lbrealizacao = new Label("Realização:", lblogo);
        palcoMenu.addActor(lbrealizacao);
    }

    private void atualizaLabelLogos(){
        lbrealizacao.setPosition(40 + spriteintegrado.getWidth() / 2, 40 + spriteintegrado.getHeight());
        lbapoio.setPosition(530 + spritecnpq.getWidth() / 2, 40 + spriteintegrado.getHeight());
    }

    @Override
    public void show() {
        cameraMenu = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        palcoMenu = new Stage();

        Gdx.input.setInputProcessor(palcoMenu);
        initTexturas();
        initFonteBotoes();
        initLabelsBotoes();
        initLabel();
        initLogos();
        initLabelLogos();

        if (!jogar) {
            palcoMenu.addActor(btnJogar);
            palcoMenu.addActor(btnCreditos);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        atualizarBotoes();
        acaoBotoes();
        atualizaLabelLogos();
        if (!btnCreditos.isChecked() && !jogar) {
            atualizaLogos();
        }else {
            lbrealizacao.remove();
            lbapoio.remove();
        }

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
        pincel.dispose();
        texturaLogoIntegrado.dispose();
        texturaLogoCnpq.dispose();
    }
}