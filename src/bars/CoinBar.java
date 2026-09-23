package bars;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinBar {
    private int coins;
    private ImageView image;

    /**
     * Vytvorý nový CoinBar, nastavi mu pociatocný pocet minci, a pridelí mu obrázok
     * @param coins pociatocny pocet minci
     */
    public CoinBar(int coins) {
        this.coins = coins;
        this.image = new ImageView(new Image("/images/items/coin.png"));
        this.image.setLayoutX(20);
        this.image.setLayoutY(300);
        this.image.setFitWidth(40);
        this.image.setFitHeight(40);
        this.image.setUserData(this);
    }

    /**
     * vrati obrazok minci
     * @return vrati obrazok minci
     */
    public ImageView getImage() {
        return this.image;
    }
}
