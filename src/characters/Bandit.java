package characters;

import akcie.CharacterCategory;
import bars.HpBar;
import items.Coin;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import maps.Location;

import java.util.Random;

public class Bandit extends Character implements IKillable, IMoveable {

    private boolean isAttacked;
    private HpBar hpBar;
    private Pane enviroment;


    /**
     * Vytvorí banditu s menom, x a y súradnicami a lokáciou
     * @param name meno banditu
     * @param x počiatočná x-ová súradnica banditu
     * @param y pociatočná y-ova súradnica banditu
     * @param location lokacia v ktorej sa bandita nachádza
     */
    public Bandit(String name, int x, int y, Location location) {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/npc/bandit.png"))));
        this.setCharacterCategory(CharacterCategory.BANDIT);
        this.setHealth(this.getMaxHealth());
        this.setName(name);
        this.setX(x);
        this.setY(y);
        this.setHeight(90);
        this.setWidth(80);
        this.getImage().setLayoutY(y);
        this.getImage().setLayoutX(x);
        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setUserData(this);
        this.movement();
        this.hpBar = new HpBar(x , y, this.getHealth());
        this.hpBar.getHpBar().setVisible(this.isAttacked);
        this.setAlive(true);
        this.setCurrentLocation(location);
    }

    /**
     * Timeline je kód z AI
     * kazdu pol sekundu sa bandita posunie náhodne podla Random čísla, do jedného zo štyroch smerov, zároveň, kontroluje či na neho bolo zaútočené, ak áno vola metodu útoku.
     */
    private void movement() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            if (this.getAttackedBy() != null && this.enviroment != null) {
                this.attack(this.getAttackedBy(), 10, this.enviroment);
            }

            Random rand = new Random();
            switch (rand.nextInt(1, 5)) {
                case 1 -> this.moveY(-20);
                case 2 -> this.moveY(20);
                case 3 -> this.moveX(-20);
                case 4 -> this.moveX(20);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    /**
     * Ak nastane kolízia s postavou na ktorú sa dá zaútočiť, tak zautočí za hodnotu amount
     * @param creature postava na ktorú utočí
     * @param amount množstvo poškodenia ktoré udelí
     * @param pane hracia plocha
     */
    @Override
    public void attack(Character creature, int amount, Pane pane) {
        if (this.collision(creature) && this.isAlive()) {
            if (creature instanceof IKillable) {
                ((IKillable)creature).takeDamage(amount, pane, this);
                this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/npc/banditAttack.png")));
                PauseTransition pause = new PauseTransition(Duration.millis(300));
                pause.setOnFinished(e -> this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/npc/bandit.png"))));
                pause.play();
            }
        }


    }

    /**
     * vráti true ak sa dva objekti prekryli
     * @param otherObject objekt s ktorým kontrolujeme prekrytie
     * @return true ak sa prekryli
     */
    public boolean collision(GameObject otherObject) {
        return this.getImage().getBoundsInParent().intersects(otherObject.getImage().getBoundsInParent());
    }

    /**
     * vráti healthBar
     * @return healthBar
     */
    @Override
    public ProgressBar getHpBar() {
        return this.hpBar.getHpBar();
    }


    /**
     * vráti informáciu o banditovy
     * @return info
     */
    public String getInfo() {
        return "bandit";
    }


    /**
     * pozrie sa či sa hodnotu parametra môže posunúť na osi X, ak áno posunie sa
     * @param x hodnota o kolko sa má posunúť
     */
    @Override
    public void moveX(double x) {
        int novaX = (int)(this.getX() + x);

        if (!this.getCurrentLocation().isWall(novaX , (int)this.getY() + 60)) {
            this.setX(novaX);
            this.getImage().setLayoutX(this.getX());
            this.hpBar.moveBarX((int)this.getX());
        }

    }


    /**
     * pozrie sa či sa hodnotu parametra môže posunúť na osi Y, ak áno posunie sa
     * @param y hodnota o kolko sa má posunúť
     */
    @Override
    public void moveY(double y) {
        int novaY = (int)(this.getY() + y);

        if (!this.getCurrentLocation().isWall((int)this.getX(), novaY + 60)) {
            this.setY(novaY);
            this.getImage().setLayoutY(this.getY());
            this.hpBar.moveBarY((int)this.getY());
        }
    }


    /**
     * Utrpí poškodenie, nastaví si hodnotu, že na neho bolo zautočené a aj od koho. Ak mu klesne klesne hodnota zdravia pod 0, vymaže sa z hracej plochy a zanechá po sebe Coiny
     * @param amount kolko poškodenia ma utrpieť
     * @param pane hracia plocha
     * @param hero postava hráča od ktorej poškodenie utrpel
     */
    @Override
    public void takeDamage(int amount, Pane pane, IKillable hero) {
        this.decreaseHealth(amount);
        this.isAttacked = true;
        this.hpBar.getHpBar().setVisible(this.isAttacked);
        this.hpBar.changeHealth(this.getHealth());
        this.enviroment = pane;
        this.setAttackedBy((Character)hero);
        if (this.getHealth() < 0) {
            pane.getChildren().remove(this.getImage());
            pane.getChildren().remove(this.hpBar.getHpBar());
            this.setAlive(false);
            pane.getChildren().add(new Coin(this.getX(), this.getY(), 30).getImage());
        }
    }

    /**
     *
     * @param hero postava hráča
     * @param pane hracia plocha
     */
    @Override
    public void interact(Hero hero, Pane pane) {
        return;
    }


}
