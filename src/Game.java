

import akcie.Direction;
import bars.QuestBar;
import characters.GameObject;
import characters.Hero;
import characters.Character;
import characters.IKillable;

import items.Door;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import maps.Castle;
import maps.Location;
import maps.MapManager;
import maps.Village;
import maps.Forest;
import maps.Dungeon;

public class Game extends Application {
    private static Location startLocation;
    private Location actualLocation;
    private Pane pane;
    private Pane objectLayer;
    private MapManager mapManager;
    private Scene scene;
    private Hero hero;

    /**
     * inicializuje hernu scénu, počiatočné stavy, lokácie, vykreli mapu a hrdinu
     * {@inheritDoc}
     * @param stage hlavne okno aplikácie
     */
    @Override
    public void start(Stage stage) {
        Castle castle = (Castle)Castle.getLocation();
        Village village = (Village)Village.getLocation();
        Forest forest = (Forest)Forest.getLocation();
        Dungeon dungeon = (Dungeon)Dungeon.getLocation();

        castle.addExits();
        village.addExits();
        forest.addExits();
        dungeon.addExits();

        this.actualLocation = castle;


        this.hero = Hero.getHero("hero", this.actualLocation);
        this.hero.setCurrentLocation(this.actualLocation);

        Canvas canvas = new Canvas(1000, 800);
        canvas.setMouseTransparent(true);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.objectLayer = new Pane();
        this.pane = new Pane();
        this.pane.getChildren().add(canvas);
        this.pane.getChildren().add(this.objectLayer);
        QuestBar questBar = new QuestBar(this.hero, this.pane);
        this.pane.getChildren().add(questBar.getQuestBar());
        this.pane.setPrefSize(1000, 800);
        this.scene = new Scene(this.pane);
        this.mapManager = new MapManager(gc);

        for (GameObject obj : this.actualLocation.getObjectsInLocation()) {
            this.objectLayer.getChildren().add(obj.getImage());
            if (obj instanceof IKillable kobj) {
                this.objectLayer.getChildren().add(kobj.getHpBar());
            }
        }
        this.objectLayer.getChildren().add(this.hero.getImage());
        this.objectLayer.getChildren().add(this.hero.getHpBar());
        this.hero.moveToStartingPoint(this.actualLocation.getStartingXLeft(), this.actualLocation.getStartingY());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, 1000, 800);
                gc.fillText("Quest bar: ", 20, 20);
                Game.this.mapManager.drawMap(Game.this.actualLocation);
            }
        };
        timer.start();

        Game.this.scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W -> this.hero.move(Direction.UP);
                case S -> this.hero.move(Direction.DOWN);
                case A -> this.hero.move(Direction.LEFT);
                case D -> this.hero.move(Direction.RIGHT);
            }
            if (this.hero.getX() > 950) {
                this.changeLocation(this.actualLocation.getExits().get("vychod"), Direction.LEFT);
            }
            if (this.actualLocation.getTile((int)this.hero.getX(), (int)this.hero.getY() + 60) == 17 && this.hero.getX() < 0) {
                this.changeLocation(this.actualLocation.getExits().get("zapad"), Direction.RIGHT);
            }
            if (this.actualLocation.getTile((int)this.hero.getX(), (int)this.hero.getY() + 60) == 1 && this.hero.getX() < 0 ) {
                this.changeLocation(this.actualLocation.getExits().get("zapad"), Direction.RIGHT);
            }
        });

        Game.this.scene.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                Object obj = mouseEvent.getTarget();
                System.out.println("Kliknutý objekt: " + obj);

                if (obj instanceof ImageView ivdo && ivdo.getUserData() instanceof Door door) {
                    this.changeLocation(door.getLocationTo(), Direction.DOWN);
                }
                if (obj instanceof ImageView iobj && iobj.getUserData() instanceof GameObject gobj) {
                    this.hero.doSomething(gobj, this.objectLayer);
                }
            }
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                Object obj =  mouseEvent.getTarget();

                if (obj instanceof ImageView iobj && iobj.getUserData() instanceof IKillable gobj) {
                    this.hero.attack((Character)gobj, 40, this.objectLayer);
                    if (!gobj.isAlive()) {
                        this.objectLayer.getChildren().remove(((Character)gobj).getImage());
                    }
                    System.out.println(gobj.getHealth());
                }
            }
        });

        questBar.getQuestBar().setOnAction(actionEvent -> {
            questBar.openQuestBar();
        });

        stage.setTitle("Moja hra");
        stage.setScene(this.scene);
        stage.show();
    }

    /**
     * zmeni aktualnu lokáciu na novú, nastavy hrdinové počiatočne suradnice lokacie podla smeru, a vykreli objekti v miestnosti
     * @param newLocation nová lokacia
     * @param from z ktorého smeru vstupujeme
     */
    public void changeLocation(Location newLocation, Direction from) {
        this.objectLayer.getChildren().clear();
        this.actualLocation = newLocation;
        this.hero.setCurrentLocation(this.actualLocation);
        for (GameObject obj : this.actualLocation.getObjectsInLocation()) {
            if (obj instanceof IKillable kobj) {
                if (kobj.isAlive()) {
                    this.objectLayer.getChildren().add(kobj.getHpBar());
                    this.objectLayer.getChildren().add(obj.getImage());
                }
            } else {
                this.objectLayer.getChildren().add(obj.getImage());
            }
        }
        this.objectLayer.getChildren().add(this.hero.getImage());
        this.objectLayer.getChildren().add(this.hero.getHpBar());

        switch (from) {
            case LEFT  -> this.hero.moveToStartingPoint(newLocation.getStartingXLeft(), newLocation.getStartingY());
            case RIGHT -> this.hero.moveToStartingPoint(newLocation.getStartingXRight(), newLocation.getStartingY());
            default    -> this.hero.moveToStartingPoint(newLocation.getStartingXLeft(), newLocation.getStartingY());
        }
    }
}