package items;

import characters.GameObject;
import characters.Hero;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Item implements GameObject {

    private double x;
    private double y;
    private double width = 30;
    private double height = 30;
    private int price;
    private ImageView image;


    /**
     * Vrati info o predmete
     * @return predmet info
     */
    public abstract String getInfo();

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    public ImageView getImage() {
        return this.image;
    }

    /**
     * prida predmet do hráčovho inventára a zmaže ho z hracej plochy
     * @param hero postava hráča
     * @param pane hracia plocha
     */
    @Override
    public void interact(Hero hero, Pane pane) {
        hero.addToInventory(this);
        pane.getChildren().remove(this.image);
    }

    /**
     * Vrati cenu predmetu
     * @return cena predmetu
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * nastavy x-ovu suradncu predmetu na parameter
     * @param x x-ova suradnica
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * nastavy y-ovu suradnicu predmetu na parameter
     * @param y y-ova suradnica
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * nastavy cenu predmetu na hodnotu parametra
     * @param price cena predmetu
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * nastavy atribut image na ImageView z parametra
     * @param image ImageView predmetu
     */
    public void setImage(ImageView image) {
        this.image = image;
    }
}
