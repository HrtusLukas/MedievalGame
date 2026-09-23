package maps;

import characters.GameObject;
import characters.Warlock;
import items.Armor;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Dungeon extends Location {

    private static Dungeon dungeon;

    private Dungeon() {
        this.setLocationInfo("Dungeon");
        this.getObjectsInLocation();

        ArrayList<GameObject> objects = new ArrayList<>();
        objects.add(new Warlock(720, 280, this));
        objects.add(new Armor(500, 600));
        this.setObjectsInLocation(objects);
        this.setStartingXLeft(0);
        this.setStartingXRight(0);
        this.setStartingY(350);

        Image tile1 = new Image("/images/tileSet/DungeonTileSet/1.png");
        Image tile2 = new Image("/images/tileSet/DungeonTileSet/2.png");
        Image tile3 = new Image("/images/tileSet/DungeonTileSet/3.png");
        Image tile4 = new Image("/images/tileSet/CastleTileSet/4.png");
        Image tile5 = new Image("/images/tileSet/CastleTileSet/5.png");
        Image tile6 = new Image("/images/tileSet/CastleTileSet/6.png");
        Image tile7 = new Image("/images/tileSet/CastleTileSet/7.png");
        Image tile8 = new Image("/images/tileSet/CastleTileSet/8.png");
        Image tile9 = new Image("/images/tileSet/CastleTileSet/9.png");
        Image tile10 = new Image("/images/tileSet/CastleTileSet/10.png");
        Image tile11 = new Image("/images/tileSet/CastleTileSet/11.png");
        Image tile12 = new Image("/images/tileSet/CastleTileSet/12.png");
        Image tile13 = new Image("/images/tileSet/CastleTileSet/13.png");
        Image tile14 = new Image("/images/tileSet/CastleTileSet/14.png");
        Image tile15 = new Image("/images/tileSet/CastleTileSet/E6.png");
        Image tile16 = new Image("/images/tileSet/CastleTileSet/F6.png");

        this.setTileSet(new Image[]{tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15, tile16});

        this.setTiles(new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 2, 2, 0},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2},
                {0, 0, 0, 0, 2, 1, 2, 0, 0, 2, 2, 2, 2, 2, 2, 0},
                {0, 0, 0, 0, 2, 1, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "dungeon";
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return this.getLocationInfo();
    }


    /**
     * Vrati true ak x a y suradnica su tile 0 alebo 2
     * @param x x-ova suradnica
     * @param y y-ova suradnica
     * @return true ak su x a y suradnice stena
     */
    @Override
    public boolean isWall(double x, int y) {
        int col = (int)(x / 64);
        int row = (int)(y / 64);

        if (row < 0 || row >= this.getTiles().length || col < 0 || col >= this.getTiles()[0].length) {
            return true;
        }

        int tile = this.getTiles()[row][col];
        return tile == 0 || tile == 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addExits() {
        this.getExits().put("vychod", Castle.getLocation());
        this.getExits().put("zapad", Village.getLocation());
    }

    /**
     * Vrati inštanciu tejto lokácie
     * @return inštancii lokacie
     */
    public static Location getLocation() {
        if (dungeon == null) {
            dungeon = new Dungeon();
        }
        return dungeon;
    }
}