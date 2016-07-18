package br.grupointegrado.Projeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.util.ArrayDeque;
import java.util.Queue;


/**
 * Created by Elito Fraga on 10/09/2015.
 */
public class TelaJogo extends TelaBase {

    private static String ultimoCodigo = "";
    private OrthographicCamera cameraInformacoes;
    private SpriteBatch pincel;
    private Texture texturaBloco;
    private Texture texturaBloco2;
    private Texture texturaAgua;
    private Texture texturaFinal;
    private Texture texturaSplash;
    private Texture texturaSplashBloco;
    private Label lbAnimaLevel;
    private Label lbAnimaGameOver;
    private BitmapFont fonte;
    private Label lbContBlocos;
    private ImageTextButton btnProximo;
    private ImageTextButton btnAnterior;
    private ImageTextButton btnOk;
    private Label lbLevel;
    private Stage palcoInformacoes;
    private Stage palcoNivel;
    private Stage palcoGameOver;
    private float initX = 0;
    private float initY = -10;
    private Sprite splash;
    private Sprite splashBloco;
    private float intervalo_frames = 0;
    private final float tempo_intervaloBloco = 1 / 8f;
    private final float tempo_intervalo = 1 / 8f;
    private float intervalo_framesBloco = 0f;
    private int estagio = 0;
    private int estagioBloco = 0;
    private boolean gameOver = false;
    private int cont_blocos_remover = 0;
    private Jogador classJogador;
    private Array<Array<Bloco>> caminho = new Array<Array<Bloco>>();
    private Array<Texture> trocarSplash = new Array<Texture>();
    private Array<Texture> trocarSplashBloco = new Array<Texture>();
    private BitmapFont fonteBotoes;
    private ImageTextButton btnVoltar;
    private ImageTextButton btnCompilar;
    private ImageTextButton btnExecutar;
    private ImageTextButton btnCancelar;
    private ImageTextButton btnAjuda;
    private BitmapFont fonteAnimaNivel;
    private TextArea caixaTexto;
    private TextArea caixaErro;
    private boolean inicioJogo = false;
    private boolean executouComandos = false;
    private boolean ganhou = false;
    private boolean reiniciando = false;
    private static int contTentativas = 0;
    private Label lbContTentativas;
    private int act_tentativas = 0;
    private Skin skin;
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


    private Sound somSplash;
    private Sound somGameOver;
    private Sound somLevel;
    private Music somFundo;
    private Sound clickBotao;

    private float TAMANHO_ALTURA;
    private float TAMANHO_LARGURA;

    private Queue<Direcao> direcoes = new ArrayDeque<Direcao>();

    public TelaJogo(MainJogo jogo) {
        super(jogo);
    }

    @Override
    public void show() {
        cameraInformacoes = new OrthographicCamera(MainJogo.WIDTH, MainJogo.HEIGHT);
        cameraInformacoes.setToOrtho(false, MainJogo.WIDTH, MainJogo.HEIGHT);
        cameraInformacoes.update();

        palcoInformacoes = new Stage(new FillViewport(MainJogo.WIDTH, MainJogo.HEIGHT));
        palcoNivel = new Stage(new FillViewport(MainJogo.WIDTH, MainJogo.HEIGHT));
        palcoGameOver = new Stage(new FillViewport(MainJogo.WIDTH, MainJogo.HEIGHT));

        Gdx.input.setInputProcessor(palcoInformacoes);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        pincel = new SpriteBatch();

        initTexturas();
        TAMANHO_LARGURA = texturaBloco.getWidth() / 2;
        TAMANHO_ALTURA = texturaBloco.getHeight() / 2;

        initNivel();
        initSom();

        carregarJogoSalvo();
        initCaixaTexto();
        initCaixaErro();
        initFonteInformacoes();
        initLabelsInformacoes();
        initBotaoExecutar();
        initBotaoCancelar();
        initBotaoCompilar();
        initBotaoOK();
        initBotaoSetaD();
        initBotaoSetaE();
        initBotaoVoltar();
        initBotaoAjuda();
        initLabelAnima();
        initTextoAjuda();
        initLousa();
        initJogador();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        if (inicioJogo) {
            atualizarDirecoes();
            capturarTeclas();
            atualizaNivel();
            consoleGame();
            atualizaBotaoVoltar();
            atualizarBotaoAjuda();
            caixaTexto.act(delta);
            caixaErro.act(delta);
            lousa.act(delta);
        }
        palcoNivel.act();
        atualizarInformacoes();
        atualizarBlocos();


        reenderizarTelaJogo(delta);

        if (!gameOver && inicioJogo) {
            classJogador.atualizar(delta, pincel);
        }

        if (!inicioJogo) {
            reenderNivel(delta);

        }else if (gameOver) {
            somFundo.stop();
            reenderGameOver(delta);
        }

        palcoInformacoes.act(delta);
        palcoInformacoes.draw();

        palcoGameOver.act(delta);

    }

    private void reenderizarTelaJogo(float delta) {
        pincel.begin();

        pincel.setProjectionMatrix(cameraInformacoes.combined);
            reenderizaBlocos();

            if (blocoRemovido) {
                reenderizarSplashBloco(delta);
                if (estagioBloco == 3) {
                    blocoRemovido = false;
                    estagioBloco = 0;
                }
            }

            if (caminho.get(yAtual).get(xAtual).getTipo().equals(BlocoTipo.AGUA) && inicioJogo) {
                gameOver = true;

                if (estagio < 5) {
                    reenderSplashJogador(delta);
                }

            }else if (ganhou) {
                reiniciarJogo();
            }
        pincel.end();
    }


    private void atualizarDirecoes() {
        if (!reiniciando) {
            if (classJogador.getDirecao() == Direcao.PARADO && !direcoes.isEmpty() && executouComandos) {
                Direcao dir = direcoes.poll();
                classJogador.setDirecao(dir);
            } else if (classJogador.getDirecao() == Direcao.PARADO && direcoes.isEmpty() && executouComandos && !ganhou) {
                gameOver = true;
            }
        }
    }

    private void initTexturas() {
        texturaBloco = new Texture("Texturas/bloco.png");
        texturaBloco2 = new Texture("Texturas/bloco2.png");
        texturaAgua = new Texture("Texturas/agua.png");
        texturaFinal = new Texture("Texturas/final.png");

        for (int i = 1; i <= 6; i++) {
            texturaSplash = new Texture("Texturas/splash-" + i + ".png");
            trocarSplash.add(texturaSplash);
        }

        for (int i = 1; i <= 4; i++) {
            texturaSplashBloco = new Texture("Texturas/splashb-" + i + ".png");
            trocarSplashBloco.add(texturaSplashBloco);
        }

        splash = new Sprite(trocarSplash.get(0));
        splashBloco = new Sprite(trocarSplashBloco.get(0));

    }


    private void initSom() {
        somSplash= Gdx.audio.newSound(Gdx.files.internal("Sound/splashbloco.wav"));
        somGameOver = Gdx.audio.newSound(Gdx.files.internal("Sound/gameover.wav"));
        clickBotao = Gdx.audio.newSound(Gdx.files.internal("Sound/clickbotao.wav"));
        somFundo = Gdx.audio.newMusic(Gdx.files.internal("Sound/somfundo.mp3"));
    }

    private void initJogador() {
        float disAndarX = TAMANHO_LARGURA;
        float disAndarY = (TAMANHO_ALTURA / 3) * 2;

        float posInicialJX = (initX + jogo.getNivelAtual().getPosicaoInicial().x * TAMANHO_LARGURA - (TAMANHO_LARGURA / 2));
        float posInicialJY = (initY + jogo.getNivelAtual().getPosicaoInicial().y * TAMANHO_ALTURA - 78) - (TAMANHO_ALTURA - 78 / 2);

        classJogador = new Jogador(posInicialJX, posInicialJY, disAndarX, disAndarY);
    }

    private void initNivel() {
        for (BlocoTipo[] linha : jogo.getNivelAtual().getBlocos()) {
            criarLabirinto(linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6], linha[7], linha[8], linha[9], linha[10], linha[11], linha[12]);
        }
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

    private void initLabelsInformacoes() {
        Label.LabelStyle lbEstilo = new Label.LabelStyle();
        lbEstilo.font = fonte;
        lbEstilo.fontColor = Color.WHITE;

        lbContBlocos = new Label("Blocos Restantes", lbEstilo);
        palcoInformacoes.addActor(lbContBlocos);

        lbLevel = new Label("Level", lbEstilo);
        palcoInformacoes.addActor(lbLevel);

        lbContTentativas = new Label("Tentativa(s)", lbEstilo);
        palcoInformacoes.addActor(lbContTentativas);

        ImageTextButton.ImageTextButtonStyle estilo = new ImageTextButton.ImageTextButtonStyle();
        estilo.font = fonteBotoes;
    }

    private int level;
    private void atualizarInformacoes() {
        lbContBlocos.setPosition(initX + cameraInformacoes.viewportWidth / 2 - lbContBlocos.getPrefWidth(),cameraInformacoes.viewportHeight - lbContBlocos.getHeight());
        String blocos_total = "" + cont_blocos_remover;
        lbContBlocos.setText(cont_bloco_removido + "/" + blocos_total);

        lbLevel.setPosition(initX, cameraInformacoes.viewportHeight - lbContBlocos.getHeight());
        level = jogo.getNivelAtualIndex() + 1;
        lbLevel.setText("LEVEL " + level);

        lbContTentativas.setPosition(initX + cameraInformacoes.viewportWidth - lbContTentativas.getPrefWidth(), cameraInformacoes.viewportHeight - lbContBlocos.getHeight());
        act_tentativas = contTentativas + n_tentativas_salvo;

        lbContTentativas.setText("TENTATIVA(S): " + act_tentativas);

        btnVoltar.setPosition(MainJogo.WIDTH / 2 - btnVoltar.getPrefWidth() / 2 + btnCompilar.getPrefWidth(),
                MainJogo.HEIGHT / 25);

        btnCompilar.setPosition(MainJogo.WIDTH / 2 - btnCompilar.getPrefWidth() / 2 - 5,
        MainJogo.HEIGHT / 25);

        btnExecutar.setPosition(MainJogo.WIDTH / 2 + caixaTexto.getPrefWidth()- btnExecutar.getPrefWidth() - btnCancelar.getPrefHeight() - 5,
                MainJogo.HEIGHT / 1.7f + caixaTexto.getHeight() / 2);

        btnCancelar.setPosition(MainJogo.WIDTH / 2 + caixaTexto.getPrefWidth() - btnCancelar.getPrefWidth(),
                MainJogo.HEIGHT / 1.7f + caixaTexto.getHeight() / 2);

        btnAjuda.setPosition(MainJogo.WIDTH - MainJogo.WIDTH + btnAjuda.getPrefWidth(),
                MainJogo.HEIGHT / 25);

        lousa.setPosition(MainJogo.WIDTH / 2 - lousa.getWidth() / 2,
                MainJogo.HEIGHT / 2 - lousa.getHeight() / 2);

        btnProximo.setPosition(MainJogo.WIDTH / 2 + lousa.getWidth() / 2 + 5,
                MainJogo.HEIGHT / 2 - btnProximo.getHeight() / 2);

        btnAnterior.setPosition(MainJogo.WIDTH / 2 - btnAnterior.getWidth() - lousa.getWidth() / 2 - 5,
                MainJogo.HEIGHT / 2 - btnAnterior.getHeight() / 2);

        btnOk.setPosition(MainJogo.WIDTH / 2 - btnOk.getWidth() / 2,
                MainJogo.HEIGHT / 2 - lousa.getHeight() / 2);
    }

    private void initFonteInformacoes() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.color = Color.WHITE;
        param.size = 18;
        param.shadowOffsetX = 2;
        param.shadowOffsetY = 2;

        fonte = generator.generateFont(param);

        param.size = 16;
        param.color = Color.WHITE;
        fonteBotoes = generator.generateFont(param);

        param.size = 36;
        param.color = Color.WHITE;
        fonteAnimaNivel = generator.generateFont(param);

        generator.dispose();
    }

    private void salvarLevel() {
        Preferences prefNivel = Gdx.app.getPreferences("NIVEL" + jogo.getNivelAtualIndex());
        prefNivel.putString("CODIGO_SUCESSO", ultimoCodigo);
        prefNivel.putInteger("NUM_TENTATIVAS", act_tentativas);
        prefNivel.flush();
        ultimoCodigo = "";
        contTentativas = 0;
    }

    private void reiniciarJogo() {
        reiniciando = true;
        jogo.setScreen(new TelaJogo(jogo));
    }

    private void atualizaBotaoVoltar() {
        palcoInformacoes.addActor(btnVoltar);

        btnVoltar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickBotao.play();
                inicioJogo = false;
                somFundo.stop();
                carregou = false;
                jogo.setNivelAtual(0);
                jogo.setScreen(new Menu(jogo));
            }
        });
    }

    private Array<String> texto = new Array<String>();
    private void initTextoAjuda() {
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                texto.add("Objetivo do jogo\n" +
                        "\n" +
                        "Mova o personagem ate o bloco vermelho removendo todos os outros blocos. Os blocos são removidos quando o personagem passa por cima deles. Voce ira encontrar dois tipos de blocos, no qual o bloco branco so e possível passar uma vez, enquanto o preto e possível passar duas vezes.\n" +
                        "\n" +
                        "O nivel sera concluído quando o personagem chegar o bloco vermelho e tiver removido todos os outros blocos. Se o personagem nao chegar ao bloco vermelho, ou cair na agua, sera Game Over!");
            }else if (i == 1) {
                texto.add("Comandos\n" +
                        "\n" +
                        "Para movimentar o personagem voce deve utilizar instrucoes de programacao na linguagem JavaScript. Para isso voce deve acionar o objeto jogador e entao chamar a funcao desejada.\n" +
                        "\n" +
                        "Funções disponiveis:\n" +
                        "\n" +
                        "    jogador.cima()\n" +
                        "    jogador.baixo()\n" +
                        "    jogador.direita()\n" +
                        "    jogador.esquerda()\n");
            }else if (i == 2) {
                texto.add("Dicas\n" +
                        "\n" +
                        "Voce e livre para utilizar todos os recursos disponíveis na linguagem JavaScript, como lacos de repeticao:\n" +
                        "\n" +
                        "// repetir 10 vezes\n" +
                        "for (i = 0; i < 10; i++) {\n" +
                        "    jogador.direita();\n" +
                        "}\n" +
                        "\n" +
                        "Ou ate mesmo declaração de funcoes:\n" +
                        "\n" +
                        "// declarando a funcao\n" +
                        "function cimaDireita(){\n" +
                        "    jogador.cima();\n" +
                        "    jogador.direita();\n" +
                        "}\n" +
                        "// chamando a funcao\n" +
                        "cimaDireita();");
            }
        }
    }

    int cont = 0;
    TextArea lousa;
    private void initLousa() {
        lousa = new TextArea("", skin);

        lousa.setSize(MainJogo.WIDTH / 1.5f, MainJogo.HEIGHT);
        lousa.setColor(Color.BLUE);
        lousa.setDisabled(true);
        lousa.setText(texto.get(cont));
    }
    private void atualizarBotaoAjuda() {

        palcoInformacoes.addActor(btnAjuda);

        btnAjuda.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lbLevel.setVisible(false);
                lbContBlocos.setVisible(false);
                lbContTentativas.setVisible(false);
                btnCompilar.setVisible(false);
                btnVoltar.setVisible(false);
                btnAjuda.setVisible(false);

                palcoInformacoes.addActor(btnProximo);
                palcoInformacoes.addActor(btnAnterior);

                palcoInformacoes.addActor(lousa);
                palcoInformacoes.addActor(btnOk);
            }
        });


        btnProximo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cont < texto.size - 1 && btnProximo.isChecked()) {
                    cont += 1;
                    lousa.setText(texto.get(cont));
                }
                btnProximo.setChecked(false);
            }
        });

        btnAnterior.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cont > 0 && btnAnterior.isChecked()) {
                    cont += -1;
                    lousa.setText(texto.get(cont));
                }
                btnAnterior.setChecked(false);
            }
        });

        btnOk.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lousa.remove();
                btnProximo.remove();
                btnAnterior.remove();
                lbLevel.setVisible(true);
                lbContBlocos.setVisible(true);
                lbContTentativas.setVisible(true);
                btnOk.remove();

                btnAjuda.setVisible(true);
                btnCompilar.setVisible(true);
                btnVoltar.setVisible(true);
            }
        });
    }

    private void initCaixaTexto() {

        caixaTexto = new TextArea(ultimoCodigo, skin);
        caixaTexto.setSize(MainJogo.WIDTH / 2, MainJogo.HEIGHT / 2);
        caixaTexto.setPosition(cameraInformacoes.viewportWidth / 2 - caixaTexto.getWidth() / 2,
                cameraInformacoes.viewportHeight / 1.7f - caixaTexto.getHeight() / 2);
    }

    private void initCaixaErro() {

        caixaErro = new TextArea(erro, skin);
        caixaErro.setSize(MainJogo.WIDTH / 2, MainJogo.HEIGHT / 4.5f);
        caixaErro.setColor(Color.RED);
        caixaErro.setDisabled(true);
        caixaErro.setPosition(cameraInformacoes.viewportWidth / 2 - caixaErro.getWidth() / 2,
                cameraInformacoes.viewportHeight / 2 - caixaTexto.getHeight() + caixaErro.getHeight() / 2);
    }

    private int n_tentativas_salvo = 0;
    private static boolean carregou = false;
    private void carregarJogoSalvo() {
        if (!carregou) {
            Preferences prefNivel = Gdx.app.getPreferences("NIVEL" + jogo.getNivelAtualIndex());
            ultimoCodigo = prefNivel.getString("CODIGO_SUCESSO");
            n_tentativas_salvo = prefNivel.getInteger("NUM_TENTATIVAS", 0);
            carregou = true;
        }
    }

    private void consoleGame() {
        palcoInformacoes.addActor(btnCompilar);

        btnCompilar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                palcoInformacoes.addActor(caixaTexto);
                palcoInformacoes.addActor(caixaErro);

                palcoInformacoes.addActor(btnExecutar);
                palcoInformacoes.addActor(btnCancelar);

                btnVoltar.setVisible(false);
                btnCompilar.setVisible(false);
                btnAjuda.setVisible(false);
            }
        });

        btnExecutar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                executarComandos();
                if (caixaErro.getText().equals("")) {
                    clickBotao.play();
                    btnExecutar.remove();
                    btnCancelar.remove();

                    btnVoltar.setVisible(true);
                    btnCompilar.setVisible(true);

                    contTentativas += 1;

                    caixaTexto.remove();
                    caixaErro.remove();
                }

                if (executouComandos) {
                    btnCompilar.setVisible(false);
                    btnVoltar.setVisible(false);
                }
            }
        });

        btnCancelar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickBotao.play();
                btnExecutar.remove();
                btnCancelar.remove();

                btnVoltar.setVisible(true);
                btnCompilar.setVisible(true);
                btnAjuda.setVisible(true);

                caixaTexto.remove();
                caixaErro.remove();
            }
        });

    }

    private String erro = "Mensagens de erro aparecerao aqui...";
    private void executarComandos() {

        try {
            if (!caixaTexto.getText().isEmpty()) {
                ComandosJogador comandos = new ComandosJogador();

                String source = caixaTexto.getText();
                ultimoCodigo = caixaTexto.getText();

                org.mozilla.javascript.Context ctx = org.mozilla.javascript.Context.enter();
                ctx.setOptimizationLevel(-1); // desabilita a compila��o
                Scriptable scope = ctx.initStandardObjects();
                Object wrappedOut = ctx.javaToJS(comandos, scope);
                ScriptableObject.putProperty(scope, "jogador", wrappedOut);
                Object result = ctx.evaluateString(scope, source, "<cmd>", 1, null);
                caixaErro.setText("");
                executouComandos = true;
            }

        }catch (Exception ex){
            direcoes.clear(); // limpa as dire��es em caso de erro
            erro = ex.getMessage();
            caixaErro.setText(tratarErroJavascript(erro));
        }

    }


    private String tratarErroJavascript(String mensagem) {
        String[] partes = mensagem.split("<cmd>");
        if (partes.length < 2) {
            return "Erro desconhecido: \n\n" + mensagem;
        }
        String descricao = partes[0].substring(0, partes[0].length() - 1);
        String linha = partes[1].substring(0, partes[1].length() - 1);
        return "Erro na linha " + linha + " :\n\n" + descricao;
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
                posQuedaX = classJogador.getX() + classJogador.getWidth() / 3;
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
            if (estagio == 0) {
                somSplash.play();
            }
            intervalo_frames = 0;
            estagio++;
        } else {
            intervalo_frames = intervalo_frames + delta;
        }
    }

    private void reenderSplashJogador(float delta) {
        atualizarEstagioSplash(delta);
        atualizarPosicaoSplashJogador();

        splash.setTexture(trocarSplash.get(estagio));
        splash.setSize(trocarSplash.get(estagio).getWidth() / 2, trocarSplash.get(estagio).getHeight() / 2);

        splash.draw(pincel);
    }

    private int cont_bloco_removido = 0;
    private void atualizaNivel() {
        if ((caminho.get(yAtual).get(xAtual).getTipo().equals(BlocoTipo.FINAL)) &&
                (cont_bloco_removido == cont_blocos_remover) && (classJogador.getDirecao().equals(Direcao.PARADO))) {
            ganhou = true;
            int proxNivel = jogo.getNivelAtualIndex() + 1;
            if (proxNivel < jogo.getNiveis().size) {
                salvarLevel();
                jogo.setNivelAtual(proxNivel);
            }
        }
    }

    private void criarLabirinto(BlocoTipo... tipos) {
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


    private void reenderizaBlocos() {
        Sprite spriteBloco = new Sprite(texturaAgua);
        for (int i = caminho.size - 1; i >= 0 ; i--) {
            Array<Bloco> linha = caminho.get(i);
            for (int j = 0; j < linha.size; j++) {
                Bloco bloco=linha.get(j);
                switch (bloco.getTipo()) {
                    case BLOCO:
                        spriteBloco.setTexture(texturaBloco);
                        break;
                    case BLOCO2:
                        spriteBloco.setTexture(texturaBloco2);
                        break;
                    case AGUA:
                        spriteBloco.setTexture(texturaAgua);
                        break;
                    case FINAL:
                        spriteBloco.setTexture(texturaFinal);
                        break;
                }

                atualizarBlocos(bloco, spriteBloco);
                spriteBloco.draw(pincel);
            }
        }
    }

    private void atualizarBlocos(Bloco bloco, Sprite spriteBloco) {
        Rectangle recJogador = new Rectangle();
        Rectangle recBloco = new Rectangle();

        spriteBloco.setSize(TAMANHO_LARGURA, TAMANHO_ALTURA);

        spriteBloco.setPosition(initX + bloco.getPosicao().x * (spriteBloco.getTexture().getWidth() - 50),
                initY + bloco.getPosicao().y * (spriteBloco.getTexture().getHeight() - 78));

        recJogador.set(classJogador.getX() + classJogador.getWidth() / 3, classJogador.getY() - classJogador.getHeight() / 10, classJogador.getWidth() / 3,
                classJogador.getHeight() / 7);

        recBloco.set(initX + bloco.getPosicao().x * (spriteBloco.getTexture().getWidth() - 50),
                initY + 18 + bloco.getPosicao().y * (spriteBloco.getTexture().getHeight() - 78),
                spriteBloco.getWidth(), spriteBloco.getHeight() - 18);

        controlarPosicao(bloco, recBloco, recJogador);
    }

    private boolean blocoRemovido = false;
    private void atualizarBlocos() {
        if (xAtual > xAnterior || xAtual < xAnterior || yAtual > yAnterior || yAtual < yAnterior) {
            if (caminho.get(yAnterior).get(xAnterior).getTipo().equals(BlocoTipo.BLOCO2)) {
                caminho.get(yAnterior).get(xAnterior).setTipo(BlocoTipo.BLOCO);
            } else if (caminho.get(yAnterior).get(xAnterior).getTipo().equals(BlocoTipo.BLOCO)) {
                caminho.get(yAnterior).get(xAnterior).setTipo(BlocoTipo.AGUA);
                cont_bloco_removido += 1;
                blocoRemovido = true;
                somSplash.play();
                atualizarPosicaoSplashBloco();
            }
        }
        atualizar = true;
    }

    private int xAtual = 0; private int yAtual = 0;
    private int xAnterior = 0; private int yAnterior = 0;
    private boolean inicio = true; private boolean atualizar;
    private void controlarPosicao(Bloco bloco, Rectangle recBloco, Rectangle recJogador) {
        if (recBloco.contains(recJogador)) {
            if (inicio) {
                xAnterior = (int) bloco.getPosicao().x;
                yAnterior = (int) bloco.getPosicao().y;
                inicio = false;
            }else if (atualizar) {
                xAnterior = xAtual;
                yAnterior = yAtual;
                atualizar = false;
            }
            xAtual = (int) bloco.getPosicao().x;
            yAtual = (int) bloco.getPosicao().y;
        }
    }


    private void initLabelAnima() {
        Label.LabelStyle lbEstilo = new Label.LabelStyle();
        lbEstilo.font = fonteAnimaNivel;
        lbEstilo.fontColor = Color.WHITE;

        lbAnimaLevel = new Label("Level", lbEstilo);
        palcoNivel.addActor(lbAnimaLevel);

        lbAnimaGameOver = new Label("Game Over", lbEstilo);
        palcoGameOver.addActor(lbAnimaGameOver);
    }


    private float y = 100;
    private float velocidadeNivel = 80;
    private void reenderNivel(float delta) {
        if (y <= cameraInformacoes.viewportHeight / 1.5f) {
            y += velocidadeNivel * delta;
            lbAnimaLevel.setPosition(cameraInformacoes.viewportWidth / 2 - lbAnimaLevel.getPrefWidth() / 2, y);
            lbAnimaLevel.setText("LEVEL " + level);
            palcoNivel.draw();
        }else {
            inicioJogo = true;
            somFundo.play();
            somFundo.setVolume(0.5f);
            somFundo.setLooping(true);
        }
    }


    private float yg = 100;
    private int velocidadeGameOver = 45;
    private void reenderGameOver(float delta) {
        if (yg == 100) {
            somGameOver.play();
        }

        if (yg <= cameraInformacoes.viewportHeight / 1.5f) {
            yg += velocidadeGameOver  * delta;
            lbAnimaGameOver.setPosition(cameraInformacoes.viewportWidth / 2 - lbAnimaGameOver.getPrefWidth() / 2, yg);
            lbAnimaGameOver.setText("GAME OVER");
            palcoGameOver.draw();
        }else reiniciarJogo();

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

    private void atualizarPosicaoSplashBloco() {
        float x = 0;
        float y = 0;

        switch (classJogador.getDirecao()) {
            case ESQUERDA:
                x = initX + xAnterior * TAMANHO_LARGURA;
                y = initY + yAtual * (TAMANHO_ALTURA - 21) + TAMANHO_ALTURA / 2;
                splashBloco.setPosition(x, y);
                break;
            case DIREITA:
                x = initX + xAnterior * TAMANHO_LARGURA;
                y = initY + yAtual * (TAMANHO_ALTURA - 21) + TAMANHO_ALTURA / 2;
                splashBloco.setPosition(x, y);
                break;
            case CIMA:
                x = initX + xAtual * TAMANHO_LARGURA;
                y = initY + yAnterior * (TAMANHO_ALTURA - 21) + TAMANHO_ALTURA / 2;
                splashBloco.setPosition(x, y);
                break;
            case BAIXO:
                x = initX + xAtual * TAMANHO_LARGURA;
                y = initY + yAnterior * (TAMANHO_ALTURA - 21) + TAMANHO_ALTURA / 2;
                splashBloco.setPosition(x, y);
                break;
        }
    }

    private void atualizarEstagioSplashBloco(float delta) {
        if (intervalo_framesBloco >= tempo_intervaloBloco) {
            intervalo_framesBloco = 0;
            estagioBloco ++;
        } else {
            intervalo_framesBloco = intervalo_framesBloco + delta;
        }
    }

    private void reenderizarSplashBloco(float delta) {
        atualizarEstagioSplashBloco(delta);

        splashBloco.setTexture(trocarSplashBloco.get(estagioBloco));
        splashBloco.setSize(trocarSplash.get(estagioBloco).getWidth() / 1.2f, trocarSplash.get(estagioBloco).getHeight());

        splashBloco.draw(pincel);
    }

    @Override
    public void resize(int width, int height) {
        palcoInformacoes.getViewport().update(width,height);
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
        skin.dispose();
        clickBotao.dispose();
        texturaBtnExecutar.dispose();
        texturaBtnExecutar2.dispose();
        texturaBtnCancelar.dispose();
        texturaBtnCancelar2.dispose();
        texturabtnVoltar.dispose();
        texturabtnVoltar2.dispose();
        texturabtnCompilar.dispose();
        texturabtnCompilar2.dispose();
        texturabtnAnterior.dispose();
        texturabtnAnterior2.dispose();
        texturabtnProximo.dispose();
        texturabtnProximo2.dispose();
        texturabtnOK.dispose();
        texturabtnOK2.dispose();
        texturabtnAjuda.dispose();
        texturabtnAjuda2.dispose();
        for (int i = 1; i <= 6; i++) {
            texturaSplash.dispose();
        }
        for (int i = 1; i <= 4; i++) {
            texturaSplashBloco.dispose();
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
        somGameOver.dispose();
        somSplash.dispose();
        classJogador.getSomMovJogador().dispose();
        palcoInformacoes.dispose();
        palcoNivel.dispose();
        palcoGameOver.dispose();
        somFundo.dispose();
        pincel.dispose();
    }

    public class ComandosJogador {
        public void direita(){
            direcoes.add(Direcao.DIREITA);
            System.out.println(Direcao.DIREITA);
        }
        public void esquerda(){
            direcoes.add(Direcao.ESQUERDA);
            System.out.println(Direcao.ESQUERDA);
        }
        public void cima(){
            direcoes.add(Direcao.CIMA);
            System.out.println(Direcao.CIMA);
        }
        public void baixo(){
            direcoes.add(Direcao.BAIXO);
            System.out.println(Direcao.BAIXO);
        }

        @Override
        public String toString() {
            return "Jogador";
        }
    }
}
