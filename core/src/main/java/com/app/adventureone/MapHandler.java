package com.app.adventureone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MapHandler {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    private OrthographicCamera camera;
    private TiledMapTile tile;
    FitViewport viewport;
    public final int tileSize = 32;
    public final int maxRowSize = 15; // 640x640
    public final int maxColSize = 20; // 480x480
    private final int screenWidth = tileSize * maxColSize;
    private final int screenHeight = tileSize * maxRowSize;


    public MapHandler() {
        tiledMap = new TmxMapLoader().load("map.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap);
        tile = this.getTile(18, 2);
        if (tile.getId() == 154) {
            System.out.println("wata");
        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        viewport = new FitViewport(screenWidth, screenHeight, camera);
        camera.update();
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        viewport.update(screenWidth, screenHeight);
        tmr.setView((OrthographicCamera) viewport.getCamera());
        tmr.render();
    }

    public TiledMapTile getTile(int col, int row) {
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("Tile Layer 1");
        if (layer != null) {
            TiledMapTileLayer.Cell cell = layer.getCell(col, row);
            return cell.getTile();
        }
        return null;
    }
}
