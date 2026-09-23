package maps;

import characters.GameObject;
import characters.Peasant;
import items.Armor;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Village extends Location {

    private static Village village;

    private Village() {
        this.setLocationInfo("Village");

        ArrayList<GameObject> objects = new ArrayList<>();
        objects.add(new Peasant(530, 520, false));
        objects.add(new Peasant(100, 100, false));
        objects.add(new Peasant(740, 100, true));
        objects.add(new Peasant(690, 650, true));
        objects.add(new Armor(500, 600));
        this.setObjectsInLocation(objects);

        this.setStartingXLeft(0);
        this.setStartingXRight(950);
        this.setStartingY(350);

        Image tile1 = new Image("/images/tileSet/VillageTileSet/1.png");
        Image tile2 = new Image("/images/tileSet/VillageTileSet/h1.png");
        Image tile3 = new Image("/images/tileSet/VillageTileSet/h2.png");
        Image tile4 = new Image("/images/tileSet/VillageTileSet/h3.png");
        Image tile5 = new Image("/images/tileSet/VillageTileSet/h4.png");
        Image tile6 = new Image("/images/tileSet/VillageTileSet/house_part_1.png");
        Image tile7 = new Image("/images/tileSet/VillageTileSet/house_part_2.png");
        Image tile8 = new Image("/images/tileSet/VillageTileSet/house_part_3.png");
        Image tile9 = new Image("/images/tileSet/VillageTileSet/house_part_4.png");
        Image tile10 = new Image("/images/tileSet/VillageTileSet/house_part_5.png");
        Image tile11 = new Image("/images/tileSet/VillageTileSet/house_part_6.png");
        Image tile12 = new Image("/images/tileSet/VillageTileSet/house_part_7.png");
        Image tile13 = new Image("/images/tileSet/VillageTileSet/house_part_8.png");
        Image tile14 = new Image("/images/tileSet/VillageTileSet/studna.png");
        Image tile15 = new Image("/images/tileSet/VillageTileSet/pole.png");
        Image tile16 = new Image("/images/tileSet/VillageTileSet/house_part_7.png");
        Image tile17 = new Image("/images/tileSet/VillageTileSet/house_part_8.png");
        Image tile18 = new Image("/images/tileSet/ForestTileSet/road.png");

        this.setTileSet(new Image[]{tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18});

        this.setTiles(new int[][] {
                {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  2,  0},
                {0,  5,  6,  7,  8,  1,  2,  0, 14, 14, 14, 14,  0,  3,  4,  0},
                {0,  9, 10, 11, 12,  3,  4,  0, 14, 14, 14, 14,  0, 17, 17,  0},
                {0,  0,  0, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,  0},
                {0,  0,  0, 17, 13,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                {0,  0,  0, 17,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                {17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17},
                {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  5,  6,  7,  8,  0,  0},
                {0,  1,  2,  0,  0,  0,  0,  0,  0,  0,  9, 10, 11, 12,  0,  0},
                {0,  3,  4,  0,  0, 14, 14, 14, 14, 14, 14, 13,  0,  0,  0,  0},
                {0,  0,  0,  0,  0, 14, 14, 14, 14, 14, 14,  0,  0,  0,  0,  0},
                {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "village";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return this.getLocationInfo();
    }


    /**
     * Vrati true ak x a y suradnica nie su tile 0,17,15,12,10
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
        return tile != 0 && tile != 17 && tile != 15 && tile != 12 && tile != 10;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addExits() {
        this.getExits().put("vychod", Dungeon.getLocation());
        this.getExits().put("zapad", Forest.getLocation());
    }

    /**
     * Vrati inštanciu tejto lokácie
     * @return inštancii lokacie
     */
    public static Location getLocation() {
        if (village == null) {
            village = new Village();
        }
        return village;
    }
}