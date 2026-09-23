package items;

import characters.Hero;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Coin extends Item {

    private int amount;

    /**
     * Vytvori inštanciu Coin so zadaným množstvom penazí
     * @param amount kolko penazi
     */
    public Coin(int amount) {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/items/coin.png"))));
        this.amount = amount;
        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setUserData(this);
    }

    /**
     * Vytvori inštanciu Coin so zadaným množstvom penazí, x a y súradnicami
     * @param x počiatočná x-ova súradnica
     * @param y počiatočná y-ová súradnica
     * @param amount kolko penazi
     */
    public Coin(double x, double y, int amount) {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/items/coin.png"))));
        this.amount = amount;
        this.setX(x);
        this.setY(y);
        this.getImage().setFitWidth(30);
        this.getImage().setFitHeight(30);
        this.getImage().setLayoutX(x);
        this.getImage().setLayoutY(y);
        this.getImage().setUserData(this);
    }

    /**
     * Vrati počet penazi inštancie
     * @return počet penazí
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * zvýši počet penazi
     * @param amount o kolko
     */
    public void addAmount(int amount) {
        this.amount += amount;
    }

    /**
     * zníží počet penazi
     * @param amount o kolko
     */
    public void reduceAmount(int amount) {
        this.amount -= amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return "coin";
    }

    /**
     * Vrati počet penazi inštancie
     * @return počet penazí
     */
    @Override
    public int getPrice() {
        return this.amount;
    }

    /**
     * prida peniaze do hráčovho inventára
     * @param hero postava hráča
     * @param pane hracia plocha
     */
    @Override
    public void interact(Hero hero, Pane pane) {
        hero.addCoins(this.amount);
        pane.getChildren().remove(this.getImage());
    }
}