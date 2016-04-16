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

    private OrthographicCamera cameraInformacoes;
    private SpriteBatch pincel;
    private Texture texturaBloco;
    private Texture texturaBloco2;
    private Texture texturaAgua;
    private Texture texturaFinal;
    private Texture texturaSplash;
    private Texture texturaSplashBloco;
    private Label lbAnimaLevel;
    private BitmapFont fonte;
    private Label lbContBlocos;
    private Label lbLevel;
    private Stage palcoInformacoes;
    private Stage palcoNivel;
    private float initX = 0;
    private float initY = 0;
    private Sprite splash;
    private Sprite splashBloco;
    private float intervalo_frames = 0;
    private final float tempo_intervaloBloco = 1 / 8f;
    private final float tempo_intervalo = 1 / 6f;
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
    private Texture texturaBotao;
    private Texture texturaBotaoPressionado;
    private ImageTextButton btnVoltar;
    private BitmapFont fonteAnimaNivel;
    private boolean inicioJogo = false;

    public TelaJogo(MainJogo jogo) {
        super(jogo);
    }



    @Override
    public void show() {

        cameraInformacoes = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        palcoInformacoes = new Stage();
        palcoNivel = new Stage(new FillViewport(cameraInformacoes.viewportWidth, cameraInformacoes.viewportHeight, cameraInformacoes));

        Gdx.input.setInputProcessor(palcoInformacoes);

        pincel = new SpriteBatch();

        initTexturas();
        initNivel();

        initFonteInformacoes();
        initLabelsInformacoes();
        initLabelAnimaNivel();
        initJogador();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (inicioJogo) {
            capturarTeclas();
            atualizaNivel();
            atualizaBotaoVoltar();
            palcoInformacoes.act();
            palcoNivel.act();
        }

        atualizarBlocos();
        atualizarInformacoes();

        reenderizaBlocos();

        if (gameOver || !inicioJogo) {
            reenderNivel(delta);
        }

        if (blocoRemovido) {
            reenderizarSplashBloco(delta);
            if (estagioBloco == 3) {
                blocoRemovido = false;
                estagioBloco = 0;
            }
        }

        if (!gameOver && inicioJogo) {
            classJogador.atualizar(delta, pincel);
            palcoInformacoes.draw();
        }else if (gameOver){
            reenderSplashJogador(delta);
            if (estagio == 5) {
                reiniciarJogo();
            }
        }

        if (caminho.get(yAtual).get(xAtual).getTipo().equals(BlocoTipo.AGUA) && inicioJogo) {
            gameOver = true;
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

        texturaBotao = new Texture("Texturas/button.png");
        texturaBotaoPressionado = new Texture("Texturas/button-down.png");

        splash = new Sprite(trocarSplash.get(0));
        splashBloco = new Sprite(trocarSplashBloco.get(0));

    }

    private void initJogador() {
        float disAndarX = texturaBloco.getWidth();
        float disAndarY = texturaBloco.getHeight();

        float posInicialJX = initX + jogo.getNivelAtual().getPosicaoInicial().x * texturaBloco.getWidth() - texturaBloco.getWidth() / 2;
        float posInicialJY = initY + jogo.getNivelAtual().getPosicaoInicial().y * (texturaBloco.getHeight() - texturaBloco.getHeight() / 3);

        classJogador = new Jogador(posInicialJX, posInicialJY, disAndarX, disAndarY);
    }

    private void initNivel() {
        for (BlocoTipo[] linha : jogo.getNivelAtual().getBlocos()) {
            criarLabirinto(linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6], linha[7], linha[8], linha[9], linha[10], linha[11]);
        }
    }

    private void initLabelsInformacoes() {
        Label.LabelStyle lbEstilo = new Label.LabelStyle();
        lbEstilo.font = fonte;
        lbEstilo.fontColor = Color.WHITE;

        lbContBlocos = new Label("Blocos Restantes", lbEstilo);
        palcoInformacoes.addActor(lbContBlocos);

        lbLevel = new Label("Level", lbEstilo);
        palcoInformacoes.addActor(lbLevel);

        ImageTextButton.ImageTextButtonStyle estilo = new ImageTextButton.ImageTextButtonStyle();
        estilo.font = fonteBotoes;
        estilo.up = new SpriteDrawable(new Sprite(texturaBotao));
        estilo.down = new SpriteDrawable(new Sprite(texturaBotaoPressionado));

        btnVoltar = new ImageTextButton("  Voltar  ", estilo);
    }

    private int level;
    private void atualizarInformacoes() {
        lbContBlocos.setPosition(cameraInformacoes.viewportWidth / 2 - 19, cameraInformacoes.viewportHeight - lbContBlocos.getHeight());
        String blocos_total = "" + cont_blocos_remover;
        lbContBlocos.setText(cont_bloco_removido + "/" + blocos_total);

        lbLevel.setPosition(cameraInformacoes.viewportWidth / 6, cameraInformacoes.viewportHeight - lbContBlocos.getHeight());
        level = jogo.getNivelAtualIndex() + 1;
        lbLevel.setText("LEVEL " + level);

        btnVoltar.setPosition(cameraInformacoes.viewportWidth / 2 - btnVoltar.getPrefWidth() + 490,
                cameraInformacoes.viewportHeight / 2 - btnVoltar.getPrefHeight() - 200);
    }

    private void initFonteInformacoes() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.color = Color.WHITE;
        param.size = 24;
        param.shadowOffsetX = 2;
        param.shadowOffsetY = 2;

        fonte = generator.generateFont(param);

        param.size = 32;
        param.color = Color.WHITE;
        fonteBotoes = generator.generateFont(param);

        param.size = 72;
        param.color = Color.WHITE;
        fonteAnimaNivel = generator.generateFont(param);

        generator.dispose();
    }

    private void salvarLevel() {
        Preferences pref = Gdx.app.getPreferences("JOGOBLOCOS");
        pref.putInteger("MAIOR_LEVEL", jogo.getNivelAtualIndex());
        pref.flush();
    }

    private void reiniciarJogo() {
        jogo.setScreen(new TelaJogo(jogo));
        //Gdx.app.exit();
    }

    private void atualizaBotaoVoltar() {
        palcoInformacoes.addActor(btnVoltar);

        btnVoltar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inicioJogo = false;
                jogo.setScreen(new Menu(jogo));
            }
        });
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
            intervalo_frames = 0;
            estagio ++;
            if (estagio > 5)
                estagio = 0;
        } else {
            intervalo_frames = intervalo_frames + delta;
        }
    }

    private void reenderSplashJogador(float delta) {
        atualizarEstagioSplash(delta);
        atualizarPosicaoSplashJogador();

        splash.setTexture(trocarSplash.get(estagio));

        pincel.begin();
        splash.draw(pincel);
        pincel.end();
    }

    private int cont_bloco_removido = 0;
    private void atualizaNivel() {
        if ((caminho.get(yAtual).get(xAtual).getTipo().equals(BlocoTipo.FINAL)) &&
                (cont_bloco_removido == cont_blocos_remover) && (classJogador.getDirecao().equals(Direcao.PARADO))) {
            int proxNivel = jogo.getNivelAtualIndex() + 1;
            if (proxNivel < jogo.getNiveis().size) {
                jogo.setNivelAtual(proxNivel);
                salvarLevel();
                reiniciarJogo();
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
        pincel.begin();
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
        pincel.end();
    }

    private void atualizarBlocos(Bloco bloco, Sprite spriteBloco) {
        Rectangle recJogador = new Rectangle();
        Rectangle recBloco = new Rectangle();

        recJogador.set(classJogador.getX(), classJogador.getY(), classJogador.getWidth() / 2,
            classJogador.getHeight() / 2);

        recBloco.set(initX + bloco.getPosicao().x * (texturaBloco.getWidth() - 4) + 8,
                initY + bloco.getPosicao().y * (texturaBloco.getHeight() - 32) + 32,
                texturaBloco.getWidth(), texturaBloco.getHeight());

        spriteBloco.setPosition(initX + bloco.getPosicao().x * spriteBloco.getTexture().getWidth(),
                initY + bloco.getPosicao().y * (spriteBloco.getTexture().getHeight() - 32));

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


    private void initLabelAnimaNivel() {
        Label.LabelStyle lbEstilo = new Label.LabelStyle();
        lbEstilo.font = fonteAnimaNivel;
        lbEstilo.fontColor = Color.WHITE;

        lbAnimaLevel = new Label("Level", lbEstilo);
        palcoNivel.addActor(lbAnimaLevel);
    }


    private float y = 100;
    private float velocidadeNivel = 200;
    private void reenderNivel(float delta) {
        if (y <= cameraInformacoes.viewportHeight / 1.8f) {
            y += velocidadeNivel * delta;
            lbAnimaLevel.setPosition(cameraInformacoes.viewportWidth / 2 - lbAnimaLevel.getPrefWidth() / 2, y);
            lbAnimaLevel.setText("LEVEL " + level);
            palcoNivel.draw();
        }else inicioJogo = true;
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
                x = initX + xAnterior * texturaBloco.getWidth();
                y = initY + yAtual * (texturaBloco.getHeight() - 32) + texturaBloco.getHeight() / 2;
                splashBloco.setPosition(x, y);
                break;
            case DIREITA:
                x = initX + xAnterior * texturaBloco.getWidth();
                y = initY + yAtual * (texturaBloco.getHeight() - 32) + texturaBloco.getHeight() / 2;
                splashBloco.setPosition(x, y);
                break;
            case CIMA:
                x = initX + xAtual * texturaBloco.getWidth();
                y = initY + yAnterior * (texturaBloco.getHeight() - 32) + texturaBloco.getHeight() / 2;
                splashBloco.setPosition(x, y);
                break;
            case BAIXO:
                x = initX + xAtual * texturaBloco.getWidth();
                y = initY + yAnterior * (texturaBloco.getHeight() - 32) + texturaBloco.getHeight() / 2;
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

        pincel.begin();
        splashBloco.draw(pincel);
        pincel.end();
    }

    @Override
    public void resize(int width, int height) {
        palcoInformacoes.getViewport().update(width, height);
        palcoInformacoes.getCamera().update();
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
        texturaBotao.dispose();
        texturaBotaoPressionado.dispose();

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
        palcoInformacoes.dispose();
        palcoNivel.dispose();
        pincel.dispose();
    }
}
