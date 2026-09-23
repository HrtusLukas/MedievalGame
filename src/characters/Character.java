package characters;

import akcie.CharacterCategory;
import javafx.scene.image.ImageView;
import maps.Location;

public abstract class Character implements GameObject {

    private int health;
    private final int maxHealth = 100;
    private String name;
    private boolean isAlive;
    private CharacterCategory characterCategory;
    private Location currentLocation;
    private int x;
    private int y;
    private Character attackedBy;
    private int width;
    private int height;
    private ImageView image;

    /**
     * vrati maximalný počet životov aky môže postava dosiahnuť
     * @return maxHealth
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * vrati kolko životov má aktualne postava
     * @return počet životov
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * vrati informaciu o postave
     * @return info
     */
    public abstract String getInfo();

    /**
     * nastavi počet životov na hodnotu z parametra
     * @param health počet životov
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * zvýši počet životov postavy o zadaní parameter
     * @param amount koľko životov pridať
     */
    public void addHealth(int amount) {
        this.health += amount;
    }

    /**
     * znizi počet životov postavy o zadaní parameter
     * @param amount kolko životov odobrať
     */
    public void decreaseHealth(int amount) {
        this.health -= amount;
    }

    /**
     * vrati String mena
     * @return meno postavy
     */
    public String getName() {
        return this.name;
    }

    /**
     * nastavi hodnotu atributu name podla parametra
     * @param name meno
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * vrati hodnotu atributu isAlive podla toho či postava je na žive
     * @return true ak žije
     */
    public boolean isAlive() {
        return this.isAlive;
    }


    /**
     * nastavi boolean hodnotu isAlive podla parametra
     * @param alive true ak žije
     */
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    /**
     * nastavi kategoriu do ktorej postava spadá podla parametra
     * @param characterCategory kategória ktorú chceme nastaviť
     */
    public void setCharacterCategory(CharacterCategory characterCategory) {
        this.characterCategory = characterCategory;
    }

    /**
     * vrati lokáciu z atributu currentLocation
     * @return vrati lokáciu v ktorej sa postava nachádza
     */
    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    /**
     * nastavíme atribut currentLocation podla zadaného parametra
     * @param currentLocation lokacia na ktorú chceme postave nastaviť
     */
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * vrati categoriu do ktorej charakrer spadá
     * @return character category
     */
    public CharacterCategory getCharacterCategory() {
        return this.characterCategory;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public ImageView getImage() {
        return this.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * nastaví atribut x podla zadaného parametra
     * @param x x-ova súradnica ktorú chceme nastaviť
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * nastavi atribut y podla zadaného parametra
     * @param y y-ova súradnica ktorú chceme nastaviť
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * nastaví atribut attackedBy na Character z parametra
     * @param attackedBy Character ktorý zautočil
     */
    public void setAttackedBy(Character attackedBy) {
        this.attackedBy = attackedBy;
    }

    /**
     * nastavi atribut width na hodnotu parametra
     * @param width širka ktorú chceme nastaviť
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * nastavi atribut heigth na hodnotu z parametra
     * @param height výška ktorú chceme nastaviť
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * nastavi atribut image na ImageView z parametra
     * @param image ImageView ktory chceme nastaviť
     */
    public void setImage(ImageView image) {
        this.image = image;
    }

    /**
     * vrati od koho na neho bolo zautočene
     * @return kto na neho zautočil
     */
    public Character getAttackedBy() {
        return this.attackedBy;
    }
}
