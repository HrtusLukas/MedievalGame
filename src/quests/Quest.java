package quests;

import akcie.CharacterCategory;
import items.Item;

public class Quest {
    private final String questInfo;
    private final Item reward;
    private final CharacterCategory targetEnemy;
    private boolean isFinished;
    private final int requeiredAmount;
    private int currentAmount;

    /**
     * Vytvorí inštanciu questu s questInfo, reward, targetEnemy a requiredAmount
     * @param questInfo informacie o úlohe
     * @param reward odmena za splenenie
     * @param targetEnemy cielovy nepriatel
     * @param requeiredAmount potrebny počet na splnenie
     */
    public Quest(String questInfo, Item reward, CharacterCategory targetEnemy, int requeiredAmount) {
        this.questInfo = questInfo;
        this.reward = reward;
        this.targetEnemy = targetEnemy;
        this.isFinished = false;
        this.requeiredAmount = requeiredAmount;
        this.currentAmount = 0;

    }

    /**
     * Vrati informacie o ulohe
     * @return quest info
     */
    public String getQuestInfo() {
        return this.questInfo;
    }

    /**
     * Vrati odmenu v podobe predmetu
     * @return reward item
     */
    public Item getReward() {
        return this.reward;
    }

    /**
     * Vrati kategoriu cieloveho nepriatela
     * @return cielovy nepriatel kategoria
     */
    public CharacterCategory getTargetEnemy() {
        return this.targetEnemy;
    }

    /**
     * Zvýší momentalny počet
     */
    public void incrementCurrentAmount() {
        this.currentAmount++;
        if (this.currentAmount >= this.requeiredAmount) {
            this.isFinished = true;
        }
    }

    /**
     * Vrati String reprezentáciu úlohy
     * @return quest
     */
    public String getCurrentState() {
        return "(" + this.currentAmount + "/" + this.requeiredAmount + ")";
    }

    /**
     * Vrati či je úloha splnená
     * @return true ak je
     */
    public boolean isFinished() {
        return this.isFinished;
    }
}
