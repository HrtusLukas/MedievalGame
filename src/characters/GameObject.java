package characters;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public interface GameObject {

    /**
     * interakcia s objektom
     * @param hero postava hráča
     * @param pane hracia plocha
     */
    void interact(Hero hero, Pane pane);

    /**
     * vrati ImageView reprezentáciu postavy
     * @return imageView postavy
     */
    ImageView getImage();

    /**
     * vráti x-ovu súradnicu
     * @return x-ova súradníca
     */
    double getX();

    /**
     * vráti y-ovu súradnicu
     * @return y-ova súradnica
     */
    double getY();


    /**
     * vrati double hodnoty atributu width
     * @return šírka
     */
    double getWidth();

    /**
     * vrati double hodnotu atributu height
     * @return výška
     */
    double getHeight();

}

