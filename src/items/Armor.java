package items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Armor extends Item implements  IDropable {

    /**
     * Vytvori inštanciu brnenia s x a y súradnicami
     * @param x x-ova suradnica
     * @param y y-ova suradnica
     */
    public Armor(double x, double y) {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/items/armor.png"))));
        this.setPrice(80);
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
        return "armor";
    }

    /**
     * Vrati brnenie na vyhodenie s nastavenymi suradnicami dopadu
     * @param x x-ova suradnicami kde ma dopadnut
     * @param y y-ova suradnicami kde ma dopadnut
     * @return brnenie na vyhodenie
     */
    @Override
    public Item drop(double x, double y) {
        return new Armor(x, y);
    }
}
