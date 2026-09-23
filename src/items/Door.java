package items;

import characters.GameObject;
import characters.Hero;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import maps.Location;

public class Door implements GameObject {
    private ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/images/tileSet/CastleTileSet/doors.png")));
    private double x;
    private double y;
    private double width = 120;
    private double height = 70;
    private Location locationTo;

    /**
     * Vytvotí inštanciu Door s x a y súradnicami a lokáciou
     * @param x x-ová súradnica
     * @param y y-ová súradnica
     * @param location lokacia kde sa dvere nachádzajú
     */
    public Door(double x, double y, Location location) {
        this.locationTo = location;
        this.x = x;
        this.y = y;
        this.image.setLayoutX(x);
        this.image.setLayoutY(y);
        this.image.setUserData(this);
    }

    /**
     * Vrati Lokáciu do ktorej dvere smerujú
     * @return lokacia kam dvere smerujú
     */
    public Location getLocationTo() {
        return this.locationTo;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void interact(Hero hero, Pane pane) {
        return;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageView getImage() {
        return this.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }
}
