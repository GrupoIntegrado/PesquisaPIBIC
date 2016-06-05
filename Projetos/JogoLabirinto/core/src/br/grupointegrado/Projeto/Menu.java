package br.grupointegrado.Projeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    private Sound clickBotao;
    private Music somMenu;


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
        spriteintegrado.setSize(200,66);
        spriteintegrado.setPosition(570, 30);

        spritecnpq = new Sprite(texturaLogoCnpq);
        spritecnpq.setSize(220, 66);
        spritecnpq.setPosition(820, 30);
    }

    private void initSom() {
        clickBotao = Gdx.audio.newSound(Gdx.files.internal("Sound/clickbotao.wav"));
        somMenu = Gdx.audio.newMusic(Gdx.files.internal("Sound/sommenu.mp3"));
        somMenu.setLooping(true);
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
    private static int indice = 0;
    private static int indece_final = 0;
    private int aux = jogo.getNiveis().size;
    private void acaoBotoes() {
        btnJogar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickBotao.play();
                btnJogar.remove();
                btnCreditos.remove();
                jogar = true;
            }
        });

        btnCreditos.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickBotao.play();
                btnJogar.setVisible(false);
                btnCreditos.setVisible(false);
            }
        });

        btnProximo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickBotao.play();
                if (contListagem < jogo.getNiveis().size) {
                    while (aux >= 9) {
                        aux += - 9;
                    }
                    indece_final = jogo.getNiveis().size - aux;
                    if (indice < jogo.getNiveis().size && contListagem >= 9) {
                        indice += 9;
                        jogo.setNivelAtual(indice);
                        jogo.setScreen(new Menu(jogo));
                    } else if (indice == indece_final) {
                        indice = 0;
                        jogo.setNivelAtual(indice);
                        jogo.setScreen(new Menu(jogo));
                    }
                }

            }
        });

        btnAnterior.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickBotao.play();
                if (contListagem < jogo.getNiveis().size) {
                    while (aux >= 9) {
                        aux += - 9;
                    }

                    if (indice == 0) {
                        indice = jogo.getNiveis().size - aux;
                        jogo.setNivelAtual(indice);
                        jogo.setScreen(new Menu(jogo));
                    } else if (indice > 0) {
                        indice += -9;
                        jogo.setNivelAtual(indice);
                        jogo.setScreen(new Menu(jogo));
                    }
                }

            }
        });

        btnSair.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickBotao.play();
                Gdx.app.exit();
            }
        });
    }

    public void listaNivel(final int cd) {

        int nv_text = cd + 1;

        yl += 50;

        btnLista = new ImageTextButton(" NIVEL " + nv_text + " ", estilo);

        btnLista.setPosition(cameraMenu.viewportWidth / 2 - btnLista.getPrefWidth() / 2, cameraMenu.viewportHeight / 2 + 250 - yl);

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
                clickBotao.play();
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
        lbrealizacao.setPosition(spriteintegrado.getX(), spriteintegrado.getY() + spriteintegrado.getHeight() + 5);
        lbapoio.setPosition(spritecnpq.getX(), spritecnpq.getY() + spritecnpq.getHeight() + 10);
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
        initSom();

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
            while (jogo.getNivelAtualIndex() < jogo.getNiveis().size && contListagem < 9) {
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
        clickBotao.dispose();
    }
}