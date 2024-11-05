package com.app.adventureone;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asset {

    public TextureAtlas atlas;
    public Sprite sprite;
    public Vector2 position;
    public Rectangle rect;
    public int size;

    public Asset(TextureAtlas atlas, String sprite, Vector2 position, Rectangle rect, int size) {

        this.atlas = atlas;
        this.sprite = new Sprite(atlas.findRegion(sprite));
        this.sprite.setSize(size, size);
        this.position = position;
        this.rect = rect;
        this.size = size;
    }

    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
        rect.setPosition(position.x, position.y);
    }
}
