package com.app.adventureone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DeadGoblin extends Entity {

    private Goblin goblin;
    private Potion potion;

    public DeadGoblin(TextureAtlas atlas, Vector2 position, int size, Rectangle rect, int hp, int strength, Goblin goblin, Potion potion) {
        super(atlas, "goblin_dead", position, size, rect, hp, strength);
        this.goblin = goblin;
        this.potion = potion;
    }

    public void draw(SpriteBatch batch) {
        if (!goblin.isAlive()) {
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
            potion.draw(batch);
        }
    }
}
