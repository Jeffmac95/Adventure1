package com.app.adventureone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Boss extends Entity {

    public Boss(TextureAtlas atlas, Vector2 position, int size, Rectangle rect, int hp, int strength) {
        super(atlas, "boss", position, size, rect, hp, strength);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
