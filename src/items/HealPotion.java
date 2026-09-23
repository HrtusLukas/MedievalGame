package items;

import characters.Hero;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HealPotion extends Item implements IDropable, IUsable {

    /**
     * Vytvorý inštanciu HealPotion s x a y súradnicami
     * @param x
     * @param y
     */
    public HealPotion(double x, double y) {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/items/potion.png"))));
        this.setPrice(30);
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
        return "healPotion";
    }

    /**
     * prida hráčovy 50 životov
     * @param hero postava hráča
     */
    @Override
    public void use(Hero hero) {
        hero.healDamage(50);
    }

    /**
     * Vrati HealPotion na vyhodenie s nastavenymi suradnicami dopadu
     * @param x x-ova suradnicami kde ma dopadnut
     * @param y y-ova suradnicami kde ma dopadnut
     * @return HealPotion na vyhodenie
     */
    @Override
    public Item drop(double x, double y) {
        return new HealPotion(x, y);
    }
}