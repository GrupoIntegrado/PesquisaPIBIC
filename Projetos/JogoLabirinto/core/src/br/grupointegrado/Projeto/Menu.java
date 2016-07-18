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
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javafx.scene.Camera;

/**
 * Created by Elito Fraga on 27/03/2016.
 */
public class Menu extends TelaBase{

    private OrthographicCamera cameraMenu;
    private Stage palcoMenu;
    private Label lbCordenador;
    private Label lbProfessor;
    private Label lbDesenvolvedor;
    private Label lbnome1;
    private Label lbnome2;
    private Label lbnome3;
    private Label lbapoio;
    private Label lbrealizacao;
    private BitmapFont fonteBotoes;
    private BitmapFont fonte;
    private BitmapFont fontelogo;
    private BitmapFont fonteCreditos;
    private ImageTextButton btnProximo;
    private ImageTextButton btnAnterior;
    private ImageTextButton btnLista;
    private ImageTextButton btnJogar;
    private ImageTextButton btnCreditos;
    private ImageTextButton btnSair;
    private ImageTextButton btnVoltar;
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
        spriteintegrado.setSize(100,33);

        spritecnpq = new Sprite(texturaLogoCnpq);
        spritecnpq.setSize(110, 33);

    }

    private void atualizarLogoMenu() {
        spriteintegrado.setPosition(MainJogo.WIDTH - spriteintegrado.getWidth() - spritecnpq.getWidth() - 25, MainJogo.HEIGHT / 25);
        spritecnpq.setPosition(MainJogo.WIDTH - spritecnpq.getWidth() - 10, MainJogo.HEIGHT / 37);
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

        btnSair = new ImageTextButton(" Sair ", estilo);

        btnJogar = new ImageTextButton(" Jogar ", estilo);

        btnCreditos = new ImageTextButton(" Créditos ", estilo);

        btnVoltar = new ImageTextButton(" Voltar ", estilo);

        btnProximo = new ImageTextButton(" > ", estilo);

        btnAnterior = new ImageTextButton(" < ", estilo);
    }

    private void initFonteBotoes() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.size = 28;
        param.color = Color.WHITE;
        fonteBotoes = generator.generateFont(param);

        param.color = Color.BLUE;
        fonte = generator.generateFont(param);

        param.size = 18;
        fontelogo = generator.generateFont(param);

        param.size = 25;
        fonteCreditos = generator.generateFont(param);

    }

    private void atualizarBotoes() {

        btnJogar.setPosition(MainJogo.WIDTH / 2 - btnJogar.getPrefWidth() / 2,
                MainJogo.HEIGHT / 2 + btnJogar.getPrefHeight());

        btnCreditos.setPosition(MainJogo.WIDTH / 2 -btnCreditos.getPrefWidth() / 2,
                MainJogo.HEIGHT / 2 - 5);

        btnProximo.setPosition(MainJogo.WIDTH / 2 - btnVoltar.getPrefWidth() / 2 + btnAnterior.getPrefWidth() * 1.5f,
                MainJogo.HEIGHT / 25);

        btnAnterior.setPosition(MainJogo.WIDTH / 2 - btnAnterior.getPrefWidth() - 15,
                MainJogo.HEIGHT / 25);

        btnSair.setPosition(MainJogo.WIDTH - btnSair.getPrefWidth() - 10,
                MainJogo.HEIGHT / 37);

        btnVoltar.setPosition(MainJogo.WIDTH - btnVoltar.getPrefWidth() - 10,
                MainJogo.HEIGHT / 37);

    }

    private void atualizarCreditos() {

        lbnome1.setPosition(MainJogo.WIDTH / 2 - lbnome1.getPrefWidth() / 2,
                MainJogo.HEIGHT - lbCordenador.getPrefHeight() - lbnome1.getPrefHeight() - 10);
        lbnome2.setPosition(MainJogo.WIDTH / 2 - lbnome2.getPrefWidth() / 2,
                MainJogo.HEIGHT - lbnome1.getPrefHeight() - lbnome2.getPrefHeight() - lbCordenador.getPrefHeight() - lbProfessor.getPrefHeight() - 10);
        lbnome3.setPosition(MainJogo.WIDTH / 2 - lbnome3.getPrefWidth() / 2,
                MainJogo.HEIGHT - lbnome1.getPrefHeight() - lbnome2.getPrefHeight() - lbDesenvolvedor.getPrefHeight() - lbCordenador.getPrefHeight() - lbProfessor.getPrefHeight() - lbDesenvolvedor.getPrefHeight() - 10);

        lbCordenador.setPosition(MainJogo.WIDTH / 2 - lbCordenador.getPrefWidth() / 2,
                MainJogo.HEIGHT - lbCordenador.getPrefHeight() - 10);
        lbProfessor.setPosition(MainJogo.WIDTH / 2 - lbProfessor.getPrefWidth() / 2,
                MainJogo.HEIGHT - lbnome1.getPrefHeight() - lbCordenador.getPrefHeight() - lbProfessor.getPrefHeight() - 10);
        lbDesenvolvedor.setPosition(MainJogo.WIDTH / 2 - lbDesenvolvedor.getPrefWidth() / 2,
                MainJogo.HEIGHT - lbnome1.getPrefHeight() - lbDesenvolvedor.getPrefHeight() - lbCordenador.getPrefHeight() - lbProfessor.getPrefHeight() - lbDesenvolvedor.getPrefHeight() - 10);

    }

    private void atualizaBotaoVoltar() {

        btnVoltar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickBotao.play();
                btnJogar.setVisible(true);
                btnCreditos.setVisible(true);

                lbnome1.remove();
                lbnome2.remove();
                lbnome3.remove();
                lbCordenador.remove();
                lbProfessor.remove();
                lbDesenvolvedor.remove();

                lbrealizacao.setVisible(true);
                lbapoio.setVisible(true);

                atualizar = false;

                btnVoltar.remove();
            }
        });
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
                atualizar = true;
                btnCreditos.setChecked(false);
                btnJogar.setVisible(false);
                btnCreditos.setVisible(false);
                palcoMenu.addActor(btnVoltar);

                palcoMenu.addActor(lbnome1);
                palcoMenu.addActor(lbCordenador);
                palcoMenu.addActor(lbnome2);
                palcoMenu.addActor(lbProfessor);
                palcoMenu.addActor(lbnome3);
                palcoMenu.addActor(lbDesenvolvedor);

                spriteintegrado.setPosition(MainJogo.WIDTH / 2 - spriteintegrado.getWidth() / 2 - spritecnpq.getHeight() - 10,
                        MainJogo.HEIGHT / 3 - spriteintegrado.getHeight() / 2);
                spritecnpq.setPosition(MainJogo.WIDTH / 2 - spritecnpq.getWidth() / 2 + spriteintegrado.getWidth() - 10,
                        MainJogo.HEIGHT / 3 - spritecnpq.getHeight() / 2 - 5);
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

        yl += 5;

        btnLista = new ImageTextButton(" NIVEL " + nv_text + " ", estilo);

        btnLista.setPosition(MainJogo.WIDTH / 2 - btnLista.getPrefWidth() / 2,
                MainJogo.HEIGHT - btnLista.getPrefHeight() - yl);

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
                somMenu.stop();
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

    private void initLabelCreditos() {
        Label.LabelStyle lbcreditos = new Label.LabelStyle();

        lbcreditos.font = fonteCreditos;
        lbcreditos.fontColor = Color.BLUE;

        lbnome1 = new Label("Rosely Scheffer", lbcreditos);

        lbnome2 = new Label("Douglas Nassif Roma Junior", lbcreditos);

        lbnome3 = new Label("Elito Bolzani Fraga", lbcreditos);

        lbcreditos.font = fontelogo;

        lbCordenador = new Label("Cordenador(a):", lbcreditos);

        lbProfessor= new Label("Professor/Orientador:", lbcreditos);

        lbDesenvolvedor = new Label("Bolsista/Desenvolvedor:", lbcreditos);
    }

    private void atualizaLabelLogos(){
        lbrealizacao.setPosition(spriteintegrado.getX(), spriteintegrado.getY() + spriteintegrado.getHeight() + 5);
        lbapoio.setPosition(spritecnpq.getX(), spritecnpq.getY() + spritecnpq.getHeight() + 10);
    }

    private void initCamera() {
        cameraMenu = new OrthographicCamera(MainJogo.WIDTH, MainJogo.HEIGHT);
        cameraMenu.setToOrtho(false, MainJogo.WIDTH, MainJogo.HEIGHT);
        cameraMenu.update();
    }

    @Override
    public void show() {
        initCamera();
        palcoMenu = new Stage(new FillViewport(MainJogo.WIDTH, MainJogo.HEIGHT, cameraMenu));

        Gdx.input.setInputProcessor(palcoMenu);

        initTexturas();
        initFonteBotoes();
        initLabelsBotoes();
        initLabel();
        initLogos();
        initLabelLogos();
        initLabelCreditos();
        initSom();

        if (!jogar) {
            palcoMenu.addActor(btnJogar);
            palcoMenu.addActor(btnCreditos);
        }

        somMenu.play();
    }


    private boolean atualizar = false;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        atualizarBotoes();
        acaoBotoes();
        atualizaLabelLogos();
        atualizarCreditos();
        atualizaBotaoVoltar();

        pincel.setProjectionMatrix(cameraMenu.combined);


        if (!atualizar) {
            atualizarLogoMenu();
        }


        if (!jogar && !btnCreditos.isChecked()) {
            atualizaLogos();
            lbrealizacao.setVisible(true);
            lbapoio.setVisible(true);
        }else {
            lbrealizacao.setVisible(false);
            lbapoio.setVisible(false);
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
        //palcoMenu.getViewport().update(width, height);
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
        somMenu.dispose();
        palcoMenu.dispose();
        pincel.dispose();
        texturaLogoIntegrado.dispose();
        texturaLogoCnpq.dispose();
        clickBotao.dispose();
    }
}