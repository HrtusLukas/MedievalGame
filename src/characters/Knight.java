package characters;

import akcie.CharacterCategory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import maps.Location;

import java.util.Random;

public class Knight extends Character implements IMoveable {

    /**
     * Vytvorý inštanciu rytiera s menom, x a y suradnicami a lokáciou
     * @param name meno rytiera
     * @param x počiatočna x-ova suradnica rytiera
     * @param y počiatočná y-ova súradnica rytiera
     * @param location počiatočná lokácia rytiera
     */
    public Knight(String name, int x, int y, Location location) {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/npc/knight.png"))));
        this.setHealth(this.getMaxHealth());
        this.setCharacterCategory(CharacterCategory.KNIGHT);
        this.setName(name);
        this.setX(x);
        this.setY(y);
        this.setWidth(80);
        this.setHeight(90);
        this.getImage().setLayoutY(y);
        this.getImage().setLayoutX(x);
        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setUserData(this);
        this.setCurrentLocation(location);
        this.setAlive(true);
        this.movement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return "Knight, 'long live the king!'";
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void interact(Hero hero, Pane pane) {
        return;
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
        }
    }

    /**
     * Timeline je kód z AI
     * kazdu pol sekundu sa rytier posunie náhodne podla Random čísla, do jedného zo štyroch smerov
     */
    public void movement() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
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
}