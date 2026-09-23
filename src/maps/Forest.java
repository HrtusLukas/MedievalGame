package maps;

import characters.Bandit;
import characters.GameObject;
import items.Armor;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Forest extends Location {

    private static Forest forest;

    private Forest() {
        this.setLocationInfo("Forest");

        ArrayList<GameObject> objects = new ArrayList<>();
        objects.add(new Bandit("Sir Richard", 370, 220, this));
        objects.add(new Bandit("Sir William", 530, 220, this));
        objects.add(new Armor(500, 600));
        this.setObjectsInLocation(objects);

        this.setStartingXLeft(0);
        this.setStartingXRight(950);
        this.setStartingY(350);

        Image tile1 = new Image("/images/tileSet/ForestTileSet/1.png");
        Image tile2 = new Image("/images/tileSet/ForestTileSet/2.png");
        Image tile3 = new Image("/images/tileSet/ForestTileSet/3.png");
        Image tile4 = new Image("/images/tileSet/ForestTileSet/4.png");
        Image tile5 = new Image("/images/tileSet/ForestTileSet/5.png");
        Image tile6 = new Image("/images/tileSet/ForestTileSet/31.png");
        Image tile7 = new Image("/images/tileSet/ForestTileSet/32.png");
        Image tile8 = new Image("/images/tileSet/ForestTileSet/33.png");
        Image tile9 = new Image("/images/tileSet/ForestTileSet/34.png");
        Image tile10 = new Image("/images/tileSet/ForestTileSet/35tl.png");
        Image tile11 = new Image("/images/tileSet/ForestTileSet/35tr.png");
        Image tile12 = new Image("/images/tileSet/ForestTileSet/35bl.png");
        Image tile13 = new Image("/images/tileSet/ForestTileSet/35br.png");
        Image tile14 = new Image("/images/tileSet/ForestTileSet/36tl.png");
        Image tile15 = new Image("/images/tileSet/ForestTileSet/36tr.png");
        Image tile16 = new Image("/images/tileSet/ForestTileSet/36bl.png");
        Image tile17 = new Image("/images/tileSet/ForestTileSet/36br.png");
        Image tile18 = new Image("/images/tileSet/ForestTileSet/road.png");
        Image tile19 = new Image("/images/tileSet/ForestTileSet/1.png");

        this.setTileSet(new Image[]{tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18, tile19});

        this.setTiles(new int[][] {
                {0,  5,  6,  0,  0,  5,  6,  0,  0,  5,  6,  0,  0,  5,  6,  0},
                {13, 15, 12, 10, 13, 15, 12, 10, 13, 15, 12, 10, 13, 15, 12, 10},
                {7,   8, 11,  8,  7,  8, 11,  8,  7,  8, 11,  8,  7,  8, 11,  8},
                {18,  0,  0,  3,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 18},
                {18,  0,  0,  4,  0,  0,  0,  0,  3,  0,  0,  0,  0,  0,  0, 18},
                {18,  0,  0,  0,  0,  0,  0,  3,  3,  3,  0,  0,  0,  0,  0, 18},
                {17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17},
                {18,  0,  0,  0,  0,  0,  0,  0,  0,  5,  6,  0,  0,  0,  0, 18},
                {18,  0,  0,  0,  0,  0,  0,  0, 13, 15, 12, 10,  0,  0,  0, 18},
                {18,  0,  0,  0,  0,  0,  0,  0,  7,  8, 11,  8,  0,  0,  0, 18},
                {6,   5,  6,  5,  6,  5,  6,  5,  6,  5,  6,  5,  6,  5,  6,  5},
                {13, 15, 12, 15, 12, 15, 12, 15, 12, 15, 12, 15, 12, 15, 12, 10},
                {7,   8, 11,  8,  7,  8, 11,  8,  7,  8, 11,  8,  7,  8, 11,  8}
        });
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "forest";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return this.getLocationInfo();
    }

    /**
     * Vrati true ak x a y suradnica nie su 0,17,13,15,12,10,19
     * @param x x-ova suradnica
     * @param y y-ova suradnica
     * @return true ak su x a y suradnice stena
     */
    @Override
    public boolean isWall(double x, int y) {
        int col = (int)(x / 64);
        int row = (int)(y / 64);

        if (row < 0 || row >= this.getTiles().length || col < 0 || col >= this.getTiles()[0].length) {
            return false;
        }

        int tile = this.getTiles()[row][col];
        return tile != 0 && tile != 17 && tile != 13 && tile != 15 && tile != 12 && tile != 10 && tile != 19;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void addExits() {
        this.getExits().put("vychod", Village.getLocation());
        this.getExits().put("zapad", Castle.getLocation());
    }

    /**
     * Vrati inštanciu tejto lokácie
     * @return inštancii lokacie
     */
    public static Location getLocation() {
        if (forest == null) {
            forest = new Forest();
        }
        return forest;
    }
}