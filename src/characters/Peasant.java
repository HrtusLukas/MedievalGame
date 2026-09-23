package characters;

import akcie.CharacterCategory;
import items.HealPotion;
import items.IDropable;
import items.Item;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import quests.Quest;

public class Peasant extends Character implements IQuestGiver {

    private Quest questToGive;

    /**
     * Vytvorý inštanciu Peasant s x a y súradnicami, podla parametra male nastaví ImageView
     * @param x počiatočná x-ová súradnica
     * @param y počiatočná y-ová súradnica
     * @param male pohlavie
     */
    public Peasant(int x, int y, boolean male) {
        this.questToGive = new Quest("Kill bandits in the forest!", new HealPotion(50, 50), CharacterCategory.BANDIT, 2);
        this.setX(x);
        this.setY(y);
        this.setWidth(80);
        this.setHeight(90);
        this.setCharacterCategory(CharacterCategory.PEASANT);

        if (male) {
            this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/npc/farmerMan.png"))));
        } else {
            this.setImage(new ImageView(new Image(getClass().getResourceAsStream("/images/npc/farmer.png"))));
        }

        this.getImage().setFitWidth(this.getWidth());
        this.getImage().setFitHeight(this.getHeight());
        this.getImage().setLayoutX(x);
        this.getImage().setLayoutY(y);
        this.getImage().setUserData(this);
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
    public String getInfo() {
        return "peasant";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuestInfo() {
        return this.questToGive.getQuestInfo();
    }
}