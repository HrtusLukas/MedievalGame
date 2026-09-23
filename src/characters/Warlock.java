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

public class Warlock extends Character implements IKillable, IMoveable {

    private boolean isAttacked;
    private HpBar hpBar;
    private Pane enviroment;

    /**
     * Vytvotý inštanciu Warlocka s x a y súradnicami a lokáciou
     * @param x počiatočna x-ova súradnica
     * @param y počiatočná y-ova suradnica
     * @param location počiatočna lokácia
     */
    public Warlock(int x, int y, Location location) {
        this.setX(x);
        this.setY(y);
        this.setCharacterCategory(CharacterCategory.WARLOCK);
        this.setWidth(100);
        this.setHeight(140);
        this.setHealth(500);
        this.setAlive(true);
        this.isAttacked = false;
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/npc/warlock.png"))));
        this.getImage().setLayoutX(x);
        this.getImage().setLayoutY(y);
        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setUserData(this);
        this.hpBar = new HpBar(x, y, this.getHealth());
        this.hpBar.getHpBar().setVisible(this.isAttacked);
        this.setCurrentLocation(location);
        this.movement();
    }

    /**
     * Utrpí poškodenie, nastaví si hodnotu, že na neho bolo zautočené a aj od koho. Ak mu klesne klesne hodnota zdravia pod 0, vymaže sa z hracej plochy a zanechá po sebe Coiny
     * @param amount kolko poškodenia ma utrpieť
     * @param pane hracia plocha
     * @param fromWho postava hráča od ktorej poškodenie utrpel
     */
    @Override
    public void takeDamage(int amount, Pane pane, IKillable fromWho) {
        this.decreaseHealth(amount);
        this.isAttacked = true;
        this.hpBar.getHpBar().setVisible(this.isAttacked);
        this.hpBar.changeHealth(this.getHealth());
        this.setAttackedBy((Character)fromWho);
        this.enviroment = pane;
        if (this.getHealth() < 0) {
            pane.getChildren().remove(this.getImage());
            pane.getChildren().remove(this.hpBar.getHpBar());
            this.setAlive(false);
            pane.getChildren().add(new Coin(this.getX(), this.getY(), 30).getImage());
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
     * Ak nastane kolízia s postavou na ktorú sa dá zaútočiť, tak zautočí za hodnotu amount
     * @param creature na koho utočíme
     * @param amount za koľko útočíme
     * @param pane hracia plocha
     */
    @Override
    public void attack(Character creature, int amount, Pane pane) {
        if (this.collision(creature) && this.isAlive()) {
            if (creature instanceof IKillable) {
                ((IKillable)creature).takeDamage(amount, pane, this);
                ImageView flame = new ImageView(new Image(getClass().getResourceAsStream("/images/flame.png")));
                flame.setLayoutX(this.getX() - 40);
                flame.setLayoutY(this.getY());
                flame.setFitWidth(80);
                flame.setFitHeight(80);
                pane.getChildren().add(flame);

                PauseTransition pause = new PauseTransition(Duration.millis(300));
                pause.setOnFinished(e -> pane.getChildren().remove(flame));
                pause.play();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProgressBar getHpBar() {
        return this.hpBar.getHpBar();
    }

    /**
     * pozrie sa či sa hodnotu parametra môže posunúť na osi X, ak áno posunie sa
     * @param x o kolko
     */
    @Override
    public void moveX(double x) {
        int novaX = (int)(this.getX() + x);

        if (!this.getCurrentLocation().isWall(novaX, (int)this.getY() + 60)) {
            this.setX(novaX);
            this.getImage().setLayoutX(this.getX());
            this.hpBar.moveBarX((int)this.getX());
        }
    }

    /**
     * pozrie sa či sa hodnotu parametra môže posunúť na osi Y, ak áno posunie sa
     * @param y o kolko
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
     * Timeline je kód z AI
     * kazdu pol sekundu sa warlock posunie náhodne podla Random čísla, do jedného zo štyroch smerov, zároveň, kontroluje či na neho bolo zaútočené, ak áno vola metodu útoku.
     */
    private void movement() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            if (!this.isAttacked) {
                return;
            }

            if (this.getAttackedBy() != null && this.enviroment != null) {
                this.attack(this.getAttackedBy(), 50, this.enviroment);
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
     * {@inheritDoc}
     */
    @Override
    public void interact(Hero hero, Pane pane) {
        return;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return "warlock";
    }
}