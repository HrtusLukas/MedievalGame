package characters;

import akcie.CharacterCategory;
import bars.MerchantOffer;
import items.Item;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Merchant extends Character {

    private final ArrayList<Item> offer;

    /**
     * Vytvorý inštanciu Merchanta s x a y súradnicami a ponukou
     * @param x počiatočná x-ová súradnica
     * @param y počiatočná y-ová súradnica
     * @param offer ponuka
     */
    public Merchant(int x, int y, ArrayList<Item> offer) {
        this.setX(x);
        this.setY(y);
        this.setWidth(80);
        this.setHeight(90);
        this.setCharacterCategory(CharacterCategory.MERCHANT);
        this.offer = offer;
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/npc/merchant.png"))));
        this.getImage().setLayoutX(x);
        this.getImage().setLayoutY(y);
        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setUserData(this);
    }


    /**
     * Zobrazí MerchantOffer
     * @param hero postava hráča
     * @param pane hracia plocha
     */
    @Override
    public void interact(Hero hero, Pane pane) {
        var merchantOffer = new MerchantOffer(pane, hero, this.offer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return "merchant";
    }
}