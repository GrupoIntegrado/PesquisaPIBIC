package br.integrado.blocos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Elito Fraga on 05/10/2015.
 */
public class CriarCorpo {

    private Body corpoJogador;
    private Sprite corpo;

    public Body criarCorpoJogador(World mundo, Texture textura){

        corpo = new Sprite(textura);

        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(corpo.getX(), corpo.getY());
        corpoJogador = mundo.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(corpo.getWidth() / 2, corpo.getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;

        Fixture fixture = corpoJogador.createFixture(fixtureDef);

        shape.dispose();

        return  corpoJogador;
    }
}
