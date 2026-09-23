package items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shield extends Item implements IDropable {

    /**
     * Vytvorí inštanciu Shield s x a y súradnicami
     * @param x x-ova súradnica
     * @param y y-ova súradnica
     */
    public Shield(double x, double y) {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/items/shield.png"))));
        this.setPrice(70);
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
        return "shield";
    }

    /**
     * Vrati štít na vyhodenie s nastavenymi suradnicami dopadu
     * @param x x-ova suradnicami kde ma dopadnut
     * @param y y-ova suradnicami kde ma dopadnut
     * @return štíť na vyhodenie
     */
    @Override
    public Item drop(double x, double y) {
        return new Shield(x, y);
    }
}