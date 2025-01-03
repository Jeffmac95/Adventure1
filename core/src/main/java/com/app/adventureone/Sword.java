package com.app.adventureone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Sword extends Weapon {

    public boolean isThere = true;

    public Sword(TextureAtlas atlas, Vector2 position, int size, Rectangle rect, int strength) {
        super(atlas, "sword", position, size, rect, strength);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (isThere) {
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
        }
    }

}
