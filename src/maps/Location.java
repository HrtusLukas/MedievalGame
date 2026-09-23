package maps;


import characters.GameObject;
import items.Item;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;



public abstract class Location {
    private String locationInfo;
    private ArrayList<GameObject> objectsInLocation = new ArrayList<GameObject>();
    private ArrayList<Item> itemsInLocation = new ArrayList<Item>();
    private HashMap<String, Location> exits = new HashMap<String, Location>();
    private int[][] tiles;
    private Image[] tileSet;
    private int startingXLeft;
    private int startingXRight;
    private int startingY;

    /**
     * Nastavý východy lokacie
     */
    public abstract void addExits();

    /**
     * prida predmet do lokácie
     * @param item pridavany predmet
     */
    public void addItemToLocation(Item item) {
        this.itemsInLocation.add(item);
    }


    /**
     * Vrati nazov lokacie
     * @return nazov lokacie
     */
    public abstract String getName();

    /**
     * Vrati informaciu o lokacii
     * @return lokacia info
     */
    public abstract String getInfo();


    /**
     * Vrati boolean hodnotu či sa na daných súradniciach nachádza stena
     * @param x x-ova suradnica
     * @param y y-ova suradnica
     * @return true ak sa na x, y suradnicich nachádza stena
     */
    public abstract boolean isWall(double x, int y);


    /**
     * Vrati štartovaciu uroveň pozicie x z lava
     * @return štartovacia x-óva suradnica z lava
     */
    public int getStartingXLeft() {
        return this.startingXLeft;
    }

    /**
     * Vrati štartovaciu uroveň pozicie x z prava
     * @return štartovacia x-óva suradnica z prava
     */
    public int getStartingXRight() {
        return this.startingXRight;
    }

    /**
     * Vrati štartovaciu uroveň pozicie y
     * @return štartovacia y-óva suradnica
     */
    public int getStartingY() {
        return this.startingY;
    }

    /**
     * Vrati číslo tile o aký sa jedná podla x a y súradníc
     * @param x x-ova suradnica
     * @param y y-ova suradnica
     * @return číslo tile
     */
    public int getTile(int x, int y) {
        int col = x / 64;
        int row = y / 64;
        if (row < 0 || row >= this.tiles.length || col < 0 || col >= this.tiles[0].length) {
            return -1;
        }
        return this.tiles[row][col];
    }

    /**
     * Vrati nemodifikovatelny list objektov v lokacií
     * @return list objektov
     */
    public List<GameObject> getObjectsInLocation() {
        return Collections.unmodifiableList(this.objectsInLocation);
    }

    /**
     * vrati pole obrazkov lokacie
     * @return pole obrazkov
     */
    public Image[] getTileSet() {
        return this.tileSet;
    }

    /**
     * vrati dvojrozmerne pole tiles
     * @return tiles
     */
    public int[][] getTiles() {
        return this.tiles;
    }


    /**
     * Vrati nemodifikovatelny list predmetov v lokacií
     * @return list predmetov
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(this.itemsInLocation);
    }

    /**
     * Vrati informaciu o lokacii
     * @return lokacia info
     */
    public String getLocationInfo() {
        return this.locationInfo;
    }

    /**
     * nastavy info o lokacii
     * @param locationInfo lokacia info
     */
    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }


    /**
     * Nastavy objekti v lokacií
     * @param objectsInLocation list objektov
     */
    public void setObjectsInLocation(ArrayList<GameObject> objectsInLocation) {
        this.objectsInLocation = objectsInLocation;
    }


    /**
     * Vráti mapu východov
     * @return mapa východov
     */
    public HashMap<String, Location> getExits() {
        return this.exits;
    }


    /**
     * nastavy tiles na parameter
     * @param tiles dvojrozmerne pole intov
     */
    public void setTiles(int[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * nastavý tileSet na pole z parametra
     * @param tileSet pole Imageov
     */
    public void setTileSet(Image[] tileSet) {
        this.tileSet = tileSet;
    }

    /**
     * nastavy štartovaciu uroveň pozicie x z lava na parameter
     * @param startingXLeft štartovacia pozicia x z lava
     */
    public void setStartingXLeft(int startingXLeft) {
        this.startingXLeft = startingXLeft;
    }

    /**
     * nastavy štartovaciu uroveň pozicie x z prava na parameter
     * @param startingXRight štartovacia pozicia X z prava
     */
    public void setStartingXRight(int startingXRight) {
        this.startingXRight = startingXRight;
    }

    /**
     * nastavy štartovaciu uroveň pozicie y na parameter
     * @param startingY štartovacia pozicia y
     */
    public void setStartingY(int startingY) {
        this.startingY = startingY;
    }

}
