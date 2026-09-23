package items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sword extends Item implements IDropable {

    /**
     * Vytvorý inštanciu Sword
     */
    public Sword() {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/items/sword.png"))));
        this.setPrice(50);
        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setUserData(this);
    }

    /**
     * Vytvorý inštanciu Sword s x a y súradnicami
     * @param x x-ová súradnica
     * @param y y-ová spradnica
     */
    public Sword(double x, double y) {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/items/sword.png"))));
        this.setPrice(50);
        this.setX(x);
        this.setY(y);
        this.getImage().setLayoutX(x);
        this.getImage().setLayoutY(y);
        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setUserData(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return "sword";
    }

    /**
     * Vrati meč na vyhodenie s nastavenymi suradnicami dopadu
     * @param x x-ova suradnicami kde ma dopadnut
     * @param y y-ova suradnicami kde ma dopadnut
     * @return meč na vyhodenie
     */
    @Override
    public Item drop(double x, double y) {
        return new Sword(x, y);
    }
}