package com.app.adventureone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Potion extends Asset {

    public boolean isThere = true;

    public Potion(TextureAtlas atlas, Vector2 position, Rectangle rect, int size) {
        super(atlas, "potion", position, rect, size);
    }

    public void draw(SpriteBatch batch) {
        if (isThere) {
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
        }
    }
}
