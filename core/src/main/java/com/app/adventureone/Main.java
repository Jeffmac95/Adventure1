package com.app.adventureone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Map;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private TextureAtlas atlas;
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private MapHandler mapHandler;
    private Player player;
    private DeadGoblin deadGoblin;
    private Goblin goblin;
    private Sword sword;
    private Hole hole;
    private Fire fire;
    private float deltaTime;
    private ButtonHandler buttonHandler;
    private MyShapeRenderer shapeRenderer;
    private Potion potion;


    @Override
    public void create() {

        atlas = new TextureAtlas(Gdx.files.internal("atlas/game_atlas.atlas"));
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        mapHandler = new MapHandler(MapHandler.Level.START);
        potion = new Potion(atlas, new Vector2(480, 320), new Rectangle(480, 320, 32, 32), 32);
        goblin = new Goblin(atlas, new Vector2(480, 288), 32, new Rectangle(480, 288, 32, 32), 75, 10);
        deadGoblin = new DeadGoblin(atlas, new Vector2(480, 288), 32, new Rectangle(480, 288, 32, 32), 0, 0, goblin, potion);
        hole = new Hole(atlas, new Vector2(608, 96), new Rectangle(608, 96, 32, 32),32);
        sword = new Sword(atlas, new Vector2(224, 256),32, new Rectangle(224, 256, 32, 32), 2);
        player = new Player(atlas, new Vector2(0,0), 32, new Rectangle(0, 0, 32, 32), goblin, 100, 25, sword, hole, mapHandler, potion);
        fire = new Fire(atlas, new Vector2(256, 256), new Rectangle(256, 256, 32, 32), 32);
        buttonHandler = new ButtonHandler(player);
        shapeRenderer = new MyShapeRenderer(player);
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1.0f);

        deltaTime += Gdx.graphics.getDeltaTime();

        mapHandler.render();
        buttonHandler.render();

        player.handleKeys(deltaTime);
        player.handleBounds();
        player.checkCollision(atlas, deltaTime);



        batch.begin();
        if (mapHandler.currentLevel == MapHandler.Level.START) {
            goblin.draw(batch);
            deadGoblin.draw(batch);
            sword.draw(batch);
            hole.draw(batch);
            fire.draw(batch);
            fire.render(deltaTime);
        }
        player.draw(batch);
        batch.end();



        if (buttonHandler.isDebug) {
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(Color.RED);
            renderer.rect(player.position.x, player.position.y, player.size, player.size);
            renderer.rect(goblin.position.x, goblin.position.y, goblin.size, goblin.size);
            renderer.rect(sword.position.x, sword.position.y, sword.size, sword.size);
            renderer.rect(potion.position.x, potion.position.y, potion.size, potion.size);
            renderer.rect(hole.position.x, hole.position.y, hole.size, hole.size);
            renderer.rect(fire.position.x, fire.position.y, fire.size, fire.size);
            renderer.end();
            shapeRenderer.render();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
        renderer.dispose();
        shapeRenderer.dispose();
        buttonHandler.stage.dispose();
        buttonHandler.font.dispose();
        player.inventory.clear();
    }
}
