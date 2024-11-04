package com.app.adventureone;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Asset {

    public TextureAtlas atlas;
    public Sprite sprite;
    public Vector2 position;
    public int size;

    public Asset(TextureAtlas atlas, String sprite, Vector2 position, int size) {

        this.atlas = atlas;
        this.sprite = new Sprite(atlas.findRegion(sprite));
        this.position = position;
        this.size = size;
    }

    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
