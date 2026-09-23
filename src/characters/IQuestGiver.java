package characters;

import quests.Quest;

public interface IQuestGiver {
    /**
     * zadá hráčový úlohu
     * @param quest úloha
     * @param hero hračova postava
     */
    void giveQuest(Quest quest, Hero hero);

    /**
     * vrati informacie o úlohe
     * @return quest info
     */
    String getQuestInfo();
}
