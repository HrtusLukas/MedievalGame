package characters;

import akcie.CharacterCategory;
import items.Armor;
import items.IDropable;
import items.Item;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import quests.Quest;

public class King extends Character implements IQuestGiver {

    private static King king;
    private final Quest questToGive;

    private King() {
        this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/npc/king.png"))));
        this.setX(460);
        this.setY(70);
        this.setHealth(this.getMaxHealth());
        this.setCharacterCategory(CharacterCategory.KING);
        this.setHeight(110);
        this.setWidth(100);
        this.questToGive = new Quest("Slain the Warlock in the dungeon", new Armor(50, 50), CharacterCategory.WARLOCK, 1);
        this.getImage().setLayoutY(this.getY());
        this.getImage().setLayoutX(this.getX());
        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setUserData(this);
    }

    /**
     * Vytvorý singleton inštanciu kráľa
     * @return inštancia kráľa
     */
    public static King getKing() {
        if (king == null) {
            king = new King();
        }
        return king;
    }

    /**
     * vrati informaciu o kralovi
     * @return info
     */
    @Override
    public String getInfo() {
        return "King Lukas I., ruler of Uniza kingdom.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void giveQuest(Quest quest, Hero hero) {
        hero.takeQuest(quest);
    }

    /**
     * pri interkcii zadá hráčovy úlohu, ak je úloha splnená položí na hraciu plochu odmenu
     * @param hero postava hráča
     * @param pane hracia plocha
     */
    @Override
    public void interact(Hero hero, Pane pane) {
        this.giveQuest(this.questToGive, hero);

        if (this.questToGive.isFinished()) {
            Item item = this.questToGive.getReward();
            if (item instanceof IDropable) {
                Item rewardItem = ((IDropable)item).drop(this.getX(), this.getY() + 10);
                pane.getChildren().add(rewardItem.getImage());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuestInfo() {
        return this.questToGive.getQuestInfo();
    }
}