package characters;

public interface IHealable {
    /**
     * vylieči životy o zadaný parameter, a posunie životy v hpBare
     * @param amount kolko vylieci
     */
    void healDamage(int amount);
}
