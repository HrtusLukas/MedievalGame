package characters;

import akcie.CharacterCategory;
import akcie.Direction;
import bars.CoinBar;
import bars.GameOver;
import bars.HeroInventoryBar;
import bars.HpBar;
import items.Armor;
import items.Coin;
import items.IDropable;
import items.IUsable;
import items.Item;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import maps.Location;
import quests.Quest;
import java.util.HashMap;

public class Hero extends Character implements IKillable, IHealable {

    private static Hero hero;
    private final HashMap<String, Item> inventory;
    private final HashMap<String, Quest> activeQuests;
    private boolean isRunning;
    private final CoinBar coinBar;
    private final HpBar hpBar;


    private Hero(String name, Location location) {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/Hero/hero.png"))));
        this.setX(100);
        this.setY(100);
        this.setWidth(80);
        this.setHeight(90);
        this.setHealth(this.getMaxHealth());
        this.setCharacterCategory(CharacterCategory.HERO);
        this.setName(name);
        this.setCurrentLocation(location);
        this.isRunning = false;
        this.inventory = new HashMap<String, Item>();
        this.activeQuests = new HashMap<String, Quest>();
        this.coinBar = new CoinBar(0);
        this.getImage().setLayoutX(this.getX());
        this.getImage().setLayoutY(this.getY());
        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setUserData(this);
        this.hpBar = new HpBar( this.getX(), this.getY(), this.getHealth());
        this.addToInventory(new Coin(60));
        this.addToInventory(new Armor(30, 40));
    }

    /**
     * Vytvorý singleton inštanciu hrdiny
     * @param name meno hrača
     * @param location počiatočna lokácia hráča
     * @return inštancia hrdinu
     */
    public static Hero getHero(String name, Location location) {
        if (hero == null) {
            hero = new Hero(name, location);
        }
        return hero;
    }

    /**
     * vrati pocet minci ktorý ma hráč v inventári
     * @return pocet minci hráča
     */
    public int getCoins() {
        Coin coins = (Coin)this.inventory.get("coin");
        return coins.getAmount();
    }

    /**
     * prida počet minci do hráčovho inventára
     * @param amount kolko pridať
     */
    public void addCoins(int amount) {
        Coin coins = (Coin)this.inventory.get("coin");
        coins.addAmount(amount);
        this.inventory.put("coin", coins);
    }

    /**
     * prida predmet do inventára
     * @param item pridávaný predmet
     */
    public void addToInventory (Item item) {
        this.inventory.put(item.getInfo(), item);
    }


    /**
     * odoberie predmet z inventára
     * @param item odoberaný predmet
     */
    public void removeFromInventory(Item item) {
        this.inventory.remove(item.getInfo());
    }

    /**
     * pridá quest do aktivných questov
     * @param quest pridavaný quest
     */
    public void takeQuest(Quest quest) {
        this.activeQuests.put(quest.getQuestInfo(), quest);
    }

    /**
     * Vrati hashMapu questov kde String je questInfo a Quest je samotný quest
     * @return hashmapa questov
     */
    public HashMap<String, Quest> getQuests() {
        return this.activeQuests;
    }

    /**
     * pozrie sa či sa hodnotu parametra môže posunúť na osi X, ak áno posunie sa. Pričom podla smerov mení svoj ImageView
     * @param x o kolko sa posunieme
     * @param direction smer do ktorého sa posúvame
     */
    public void moveX(double x, Direction direction) {
        int novaX = (int)(this.getX() + x);

        if (!this.getCurrentLocation().isWall(novaX , (int)this.getY() + 60)) {
            this.setX(novaX);
            this.getImage().setLayoutX(this.getX());
            this.hpBar.moveBarX(this.getX());
        }


        if (this.isRunning) {
            switch (direction) {
                case LEFT -> this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/Hero/heroRun.png")));
                case RIGHT -> this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/Hero/heroRun_flipped.png")));
            }

        } else {
            switch (direction) {
                case LEFT -> this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/Hero/hero_Run2.png")));
                case RIGHT -> this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/Hero/hero_Run2_flipped.png")));
            }

        }
        System.out.println(this.getX());
        this.isRunning = !this.isRunning;
    }


    /**
     * vráti true ak sa dva objekti prekryli
     * @param otherObject otherObject objekt s ktorým kontrolujeme prekrytie
     * @return true ak sa prekryli
     */
    public boolean collision(GameObject otherObject) {
        return this.getImage().getBoundsInParent().intersects(otherObject.getImage().getBoundsInParent());
    }


    /**
     * pozrie sa či sa hodnotu parametra môže posunúť na osi Y, ak áno posunie sa. Pričom podla smerov mení svoj ImageView
     * @param y y o kolko sa posunieme
     */
    public void moveY(double y) {

        int novaY = (int)(this.getY() + y);

        if (!getCurrentLocation().isWall(this.getX(), novaY + 60)) {
            this.setY(novaY);
            this.getImage().setLayoutY(this.getY());
            this.hpBar.moveBarY(this.getY());
        }


        if (!this.isRunning) {
            this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/Hero/hero.png")));
        } else {
            this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/Hero/heroRun.png")));
        }
        this.isRunning = !this.isRunning;
        System.out.println(this.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return this.getName();
    }

    /**
     * Utrpí poškodenie, nastaví si hodnotu, že na neho bolo zautočené a aj od koho. Ak mu klesne klesne hodnota zdravia pod 0 vytvorý GameOver VBox
     * @param amount kolko
     * @param pane hracia plocha
     * @param fromWho útočník
     */
    @Override
    public void takeDamage(int amount, Pane pane, IKillable fromWho) {

        if (this.inventory.containsKey("shield")) {
            this.decreaseHealth(amount - 30);
            return;
        }
        this.decreaseHealth(amount);
        this.hpBar.changeHealth(this.getHealth());

        if (this.getHealth() <= 0) {
            if (this.getImage().getScene() != null) {
                VBox gameOverView = GameOver.getView(() -> Platform.exit());
                this.getImage().getScene().setRoot(gameOverView);
            }
            return;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void healDamage(int amount) {
        this.addHealth(amount);
        this.hpBar.changeHealth(this.getHealth());
    }

    /**
     * ak je kolizia s bytosťou na ktorú chceme zaútočiť, zautočíme a udelime poškodenie podla parametra, ak máme v inventáry meč poškodenie sa zvaäčší
     * @param creature na koho útočí
     * @param amount poškodenie
     * @param pane hracia plocha
     */
    @Override
    public void attack(Character creature, int amount, Pane pane)  {

        if (this.collision(creature) && creature != this) {
            if (this.inventory.containsKey("sword")) {
                ((IKillable)creature).takeDamage(amount + 20, pane, this);
                return;
            }
            ((IKillable)creature).takeDamage(amount, pane, this);
            this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/Hero/Hero_Attack.png")));

            if (!creature.isAlive()) {
                this.questOnEnemy(creature.getCharacterCategory());
            }

            PauseTransition pause = new PauseTransition(Duration.millis(300));
            pause.setOnFinished(e -> this.getImage().setImage(new Image(getClass().getResourceAsStream("/images/Hero/hero.png"))));
            pause.play();
        }
    }

    /**
     * vyhodíme predmet z inventára na plochu.
     * @param item predmet ktorý zahadzujeme
     * @param pane hracia plocha
     */
    public void drop(Item item, Pane pane) {
        this.removeFromInventory(item);
        Item droppedItem = ((IDropable)item).drop(this.getX(), this.getY());
        pane.getChildren().add(droppedItem.getImage());
    }

    /**
     * pouzijeme predmet z inventára
     * @param item pouzity predmet
     */
    public void useItem(Item item) {
        ((IUsable)item).use(this);
        this.removeFromInventory(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProgressBar getHpBar() {
        return this.hpBar.getHpBar();
    }

    /**
     * prejde vsetky aktualne questy a ak sa prechádzana kategoria a zhoduje s parametrom, inkrementuje progress úlohy
     * @param targetEnemy kategoria ktorú hladáme
     */
    public void questOnEnemy(CharacterCategory targetEnemy) {
        for (Quest quest : this.activeQuests.values()) {
            if (quest.getTargetEnemy() == targetEnemy) {
                quest.incrementCurrentAmount();
            }
        }
    }

    /**
     * pri kolizii interaguje s objektom
     * @param object objekt s ktorým chceme interagovať
     * @param pane hracia plocha
     */
    public void doSomething(GameObject object, Pane pane ) {
        if (this.collision(object)) {
            object.interact(this, pane);
        }
    }

    /**
     * Otvorí hráčov inventár
     * @param hero postava hráča
     * @param pane hracia plocha
     */
    @Override
    public void interact(Hero hero, Pane pane) {
        var heroInventoryBar = new HeroInventoryBar(pane, hero, this.inventory);
    }

    /**
     * zavolá metodu posunu podla zvoleného smeru
     * @param direction zvolený smer
     */
    public void move(Direction direction) {
        switch (direction) {
            case UP -> this.moveY(-13);
            case DOWN -> this.moveY(13);
            case LEFT -> this.moveX(-13, direction);
            case RIGHT -> this.moveX(13, direction);
        }
    }

    /**
     * znizi počet minci o cenu predmetu a dá si ho do inventára
     * @param item predmet ktory chceme kúpiť
     */
    public void buyItem(Item item) {
        Coin coins = (Coin)this.inventory.get("coin");
        coins.reduceAmount(item.getPrice());
        this.addToInventory(item);
    }

    /**
     * nastaví hráča na počiatočné súradnice lokácie
     * @param x počiatočna x-ova suradnica v lokacií
     * @param y počiatočna y-ova suradnica v lokacií
     */
    public void moveToStartingPoint(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.getImage().setLayoutX(this.getX());
        this.getImage().setLayoutY(this.getY());
        this.hpBar.moveBarY(this.getY());
        this.hpBar.moveBarX(this.getX());
    }

    /**
     * vráti ImageView coinBaru
     * @return coinBar ImageView
     */
    public ImageView getCoinBar() {
        return this.coinBar.getImage();
    }
}
