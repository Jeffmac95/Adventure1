package com.app.adventureone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class MyShapeRenderer {
    private ShapeRenderer shapeRenderer;
    private Player player;

    public MyShapeRenderer(Player player) {
        shapeRenderer = new ShapeRenderer();
        this.player = player;
    }

    public void render() {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float numRays = 50;
        float rayLength = 100;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1f, 0, 0, 1f);

        for (int i = 0; i < numRays; i++) {
            float angle = (float) (i * 2 * Math.PI / numRays);
            float startX = player.position.x + (player.size / 2);
            float startY = player.position.y + (player.size / 2);
            float endX =  startX + rayLength * (float) Math.cos(angle);
            float endY =  startY + rayLength * (float) Math.sin(angle);
            shapeRenderer.line(startX, startY , endX, endY);
        }
        shapeRenderer.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

}
