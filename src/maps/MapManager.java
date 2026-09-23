package maps;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class MapManager {
    private GraphicsContext gc;
    private final int tilesize = 64;

    /**
     * Vytvorí inštanciu Map managera s graphic context
     * @param gc graphic context
     */
    public MapManager(GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * podla tiles a tilesImages vykresli mapu
     * @param location vykreslovana lokácia
     */
    public void drawMap(Location location) {
        this.gc.clearRect(0, 0, 1000, 800);

        int[][] tiles = location.getTiles();
        Image[] tilesImages = location.getTileSet();

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                this.gc.drawImage(tilesImages[tiles[row][col]], col * this.tilesize, row * this.tilesize, this.tilesize, this.tilesize);
            }
        }
    }


}
