package com.app.adventureone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player extends Entity {

    private final int tileSize = 32;
    private final int maxRowSize = 15;
    private final int maxColSize = 20;
    private final int screenWidth = tileSize * maxColSize;//480
    private final int screenHeight = tileSize * maxRowSize;//640
    private String direction = "down";
    private Goblin goblin;
    private Sword sword;
    public ArrayList<String> inventory;


    public Player(TextureAtlas atlas, Vector2 position, int size, Rectangle rect, Goblin goblin, int hp, int strength, Sword sword) {
        super(atlas, "hero", position, size, rect, hp, strength);
        this.goblin = goblin;
        this.sword = sword;

        inventory = new ArrayList<>();
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void handleKeys() {

        boolean isMoving = false;


        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            position.y += tileSize;
            direction = "up";
            isMoving = true;
            sprite.setRegion(atlas.findRegion("hero_backwards_walking_right"));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            position.x -= tileSize;
            direction = "left";
            isMoving = true;
            sprite.setRegion(atlas.findRegion("hero_walking_left"));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            position.y -= tileSize;
            direction = "down";
            isMoving = true;
            sprite.setRegion(atlas.findRegion("hero_walking_right_foot"));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            position.x += tileSize;
            direction = "right";
            isMoving = true;
            sprite.setRegion(atlas.findRegion("hero_walking_right"));
        }

        rect.setPosition(position.x, position.y);

        if (!isMoving) {
            switch (direction) {
                case "up":
                    sprite.setRegion(atlas.findRegion("hero_backwards"));
                    break;
                case "left":
                    sprite.setRegion(atlas.findRegion("hero_facing_left"));
                    break;
                case "right":
                    sprite.setRegion(atlas.findRegion("hero_facing_right"));
                    break;
                default:
                    sprite.setRegion(atlas.findRegion("hero"));
            }
        }
    }

    public void handleBounds() {
        if (position.x < 0) {
            position.x = 0;
        } else if (position.x > screenWidth - size) {
            position.x = screenWidth - size;
        }

        if (position.y < 0) {
            position.y = 0;
        } else if (position.y > screenHeight - size) {
            position.y = screenHeight - size;
        }
    }

    public void checkCollision(TextureAtlas atlas) {
        if (rect.overlaps(goblin.rect)) {
            battle();
            if (inventory.contains("Sword")) {
                sprite.setRegion(atlas.findRegion("hero_fighting"));
            } else {
                sprite.setRegion(atlas.findRegion("hero_fighting_empty"));
            }
        }

        if (rect.overlaps(sword.rect)) {
            if (!inventory.contains("Sword")) {
                inventory.add("Sword");
                sword.sprite.setAlpha(0);
            }
        }
    }

    public void battle() {
        char attackTurn = 'p';

        while (hp > 0 && goblin.hp > 0) {
            if (attackTurn == 'p') {
                goblin.hp -= strength;
                System.out.println("player did: " + strength + " damage. goblin hp remaining: " + goblin.hp);
                if (goblin.hp <= 0) {
                    System.out.println("goblin died");
                    goblin.hp = 0;
                    System.out.println("goblin alive? : " + goblin.isAlive());
                }
                attackTurn = 'g';
            } else {
                hp -= goblin.strength;
                System.out.println("goblin did: " + goblin.strength + " damage. player hp remaining: " + hp);
                if (hp <= 0) {
                    System.out.println("player died");
                    hp = 0;
                }
                attackTurn = 'p';
            }
        }
    }
}
