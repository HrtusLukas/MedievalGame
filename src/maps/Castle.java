package maps;

import characters.GameObject;
import characters.King;
import characters.Knight;
import characters.Merchant;
import items.Door;
import items.HealPotion;
import items.Shield;
import items.Sword;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Castle extends Location {

    private static Castle castle;

    private Castle() {
        this.setLocationInfo("Castle");

        ArrayList<GameObject> objects = new ArrayList<>();
        objects.add(King.getKing());
        objects.add(new Knight("Sir Richard", 370, 220, this));
        objects.add(new Knight("Sir William", 530, 220, this));
        objects.add(new Merchant(175, 405, new ArrayList<>(List.of(new Sword(), new Shield(30, 30), new HealPotion(30, 30)))));
        objects.add(new Shield(250, 100));
        objects.add(new Door(450, 695, Forest.getLocation()));
        this.setObjectsInLocation(objects);

        this.setStartingXLeft(480);
        this.setStartingXRight(480);
        this.setStartingY(600);

        Image tile1 = new Image("/images/tileSet/CastleTileSet/1.png");
        Image tile2 = new Image("/images/tileSet/CastleTileSet/2.png");
        Image tile3 = new Image("/images/tileSet/CastleTileSet/3.png");
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
                {1, 1, 1, 1, 10, 1, 1, 1, 1, 1, 1, 11, 1, 1, 12, 1},
                {1, 0, 0, 0,  0, 0, 0, 2, 3,  0,  0,  0, 0, 0, 13, 1},
                {1, 0, 0, 0,  0, 0, 0, 2, 3,  0,  0,  0, 0, 0,  0, 1},
                {1, 1, 1, 1,  1, 1, 1, 4, 6,  1,  1,  1, 1, 1,  1, 1},
                {8, 0, 0, 0,  0, 0, 0, 5, 7,  0,  0,  0, 0, 0,  0, 1},
                {9, 0, 0, 0,  0, 0, 0, 2, 3,  0,  0,  0, 0, 0,  0, 1},
                {1, 0, 0, 0,  0, 0, 0, 2, 3,  0,  0,  0, 0, 0,  0, 1},
                {1, 0, 0, 0,  0, 0, 0, 2, 3,  0,  0,  0, 0, 0,  0, 1},
                {1, 0, 0, 0,  0, 0, 0, 2, 3,  0,  0,  0, 0, 0,  0, 1},
                {1, 0, 0, 0,  0, 0, 0, 2, 3,  0,  0,  0, 0, 0,  0, 1},
                {1, 0, 0, 0,  0, 0, 0, 2, 3,  0,  0,  0, 0, 0,  0, 1},
                {1, 1, 1, 1,  1, 1, 1, 1, 1,  1,  1,  1, 1, 1,  1, 1},
                {1, 1, 1, 1,  1, 1, 1, 1, 1,  1,  1,  1, 1, 1,  1, 1}
        });
    }

    /**
     * Vrati true ak x a y suradnica su tile 1,8,9
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
        return tile == 1 || tile == 8 || tile == 9;
    }

    /**
     * Vrati inštanciu tejto lokácie
     * @return inštancii lokacie
     */
    public static Location getLocation() {
        if (castle == null) {
            castle = new Castle();
        }
        return castle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addExits() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "castle";
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return this.getLocationInfo();
    }
}