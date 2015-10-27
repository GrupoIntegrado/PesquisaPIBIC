package br.integrado.blocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Elito Fraga on 29/09/2015.
 */
public class TelaJogo extends TelaBase {

    private OrthographicCamera camera;
    private ShapeRenderer renderer;
    private World mundo;
    private SpriteBatch batch;
    private Jogador classJogador;
    private Sprite bloco;
    private Texture texturaBloco;
    private Texture texturaBlocoVazio;
    private Texture texturaBlocoBarreira;
    private Texture texturaBlocoComVida;
    private CriarCorpo criarCorpo = new CriarCorpo();


    private Array<Array<Bloco>> campo = new Array<Array<Bloco>>();

    public TelaJogo(MainGame game) {
        super(game);
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mundo = new World(new Vector2(0, -98f), false);
        classJogador = new Jogador();


        preencherCampo();
        classJogador.texturasJogador();
        criarCorpo.criarCorpoJogador(mundo, classJogador.texturaJogador);
    }

    private void preencherCampo() {
        preencherLinha(BlocoTipo.VAZIO, BlocoTipo.DUPLO, BlocoTipo.VAZIO, BlocoTipo.VAZIO, BlocoTipo.VAZIO, BlocoTipo.VAZIO);
        preencherLinha(BlocoTipo.SIMPLES, BlocoTipo.SIMPLES, BlocoTipo.VAZIO, BlocoTipo.VAZIO, BlocoTipo.VAZIO, BlocoTipo.VAZIO);
        preencherLinha(BlocoTipo.SIMPLES, BlocoTipo.VAZIO, BlocoTipo.VAZIO, BlocoTipo.VAZIO, BlocoTipo.VAZIO, BlocoTipo.VAZIO);
        preencherLinha(BlocoTipo.SIMPLES, BlocoTipo.DUPLO, BlocoTipo.SIMPLES, BlocoTipo.SIMPLES, BlocoTipo.SIMPLES, BlocoTipo.VAZIO);
        preencherLinha(BlocoTipo.BARREIRA, BlocoTipo.DUPLO, BlocoTipo.VAZIO, BlocoTipo.VAZIO, BlocoTipo.SIMPLES, BlocoTipo.VAZIO);
        preencherLinha(BlocoTipo.SIMPLES, BlocoTipo.DUPLO, BlocoTipo.VAZIO, BlocoTipo.VAZIO, BlocoTipo.SIMPLES, BlocoTipo.VAZIO);
        preencherLinha(BlocoTipo.SIMPLES, BlocoTipo.SIMPLES, BlocoTipo.SIMPLES, BlocoTipo.SIMPLES, BlocoTipo.SIMPLES, BlocoTipo.VAZIO);
    }

    private void preencherLinha(BlocoTipo... tipos) {
        Array<Bloco> linha = new Array<Bloco>();
        int num = campo.size;
        for (BlocoTipo tipo : tipos) {
            Vector2 posicao = new Vector2(linha.size, num);
            switch (tipo) {
                case SIMPLES:
                    linha.add(new Bloco(posicao, tipo, 1));
                    break;
                case DUPLO:
                    linha.add(new Bloco(posicao, tipo, 2));
                    break;
                default:
                    linha.add(new Bloco(posicao, tipo, 0));
                    break;
            }
        }
        campo.add(linha);
    }

    public void capiturarTeclas() {
        classJogador.esquerda = false;
        classJogador.direita= false;
        classJogador.cima = false;
        classJogador.baixo = false;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            classJogador.esquerda = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            classJogador.direita= true;
        }else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            classJogador.cima = true;
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            classJogador.baixo = true;
        }
    }

    private float tam = 50, initX = 160, initY = 60;

    @Override
    protected void onRender(float delta) {
        Gdx.gl.glClearColor(.15f, .15f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < campo.size; i++) {
            Array<Bloco> linha = campo.get(i);
            for (int j = 0; j < linha.size; j++) {
                Bloco bloco = linha.get(j);
                switch (bloco.getTipo()){
                    case VAZIO:
                        renderer.setColor(0f, 0f, 1f, 1);
                        break;
                    case BARREIRA:
                        renderer.setColor(0f, 0f, 0f, 1);
                        break;
                    case SIMPLES:
                        renderer.setColor(1f, 1f, 1f, 1);
                        break;
                    case DUPLO:
                        renderer.setColor(.5f, .5f, .5f, 1);
                        break;
                }
                renderer.rect(initX + bloco.getPosicao().x * tam + 3 * bloco.getPosicao().x,
                        initY + bloco.getPosicao().y * tam + 3 * bloco.getPosicao().y,
                        tam, tam);
            }
        }
        renderer.end();
        capiturarTeclas();
        classJogador.desenharJogador(delta);
    }

    @Override
    protected void onUpdate(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void dispose() {
        classJogador.texturaJogador.dispose();
        for (Texture texturasMovimento : classJogador.trocarTexturaDireita) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : classJogador.trocarTexturaEsquerda) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : classJogador.trocarTexturaCima) {
            texturasMovimento.dispose();
        }
        for (Texture texturasMovimento : classJogador.trocarTexturaBaixo) {
            texturasMovimento.dispose();
        }
        classJogador.batch.dispose();
        mundo.dispose();
        renderer.dispose();
    }
}