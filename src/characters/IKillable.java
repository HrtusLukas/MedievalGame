package characters;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

public interface IKillable {
    /**
     * vrati aktualny ztav životov
     * @return životy
     */
    int getHealth();

    /**
     * utrží poškodenie od utočníka
     * @param amount kolko utrží
     * @param pane hracia plocha
     * @param fromWho útočník
     */
    void takeDamage(int amount, Pane pane, IKillable fromWho);

    /**
     * zautočí na bytosť
     * @param creature na koho utočíme
     * @param amount za koľko útočíme
     * @param pane hracia plocha
     */
    void attack(Character creature, int amount, Pane pane) ;

    /**
     * vráti healthBar
     * @return healthBar
     */
    ProgressBar getHpBar();

    /**
     * vrati hodnotu atributu isAlive podla toho či postava je na žive
     * @return true ak žije
     */
    boolean isAlive();
}
