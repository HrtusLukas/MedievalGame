package bars;

import javafx.scene.control.ProgressBar;

public class HpBar {
    private final ProgressBar hpBar;
    private double x;
    private double y;
    private final int maxHp;

    /**
     * Vytvorý ProgressBar ktorý reprezentuje životy postavy
     * @param x x-ova suradnica HpBaru
     * @param y y-ova suradnica HpBaru
     * @param maxHp maximalne životy ktoré môže postava dosiahnuť
     */
    public HpBar(double x, double y, int maxHp) {
        this.hpBar = new ProgressBar(1.0);
        this.maxHp = maxHp;
        this.hpBar.setLayoutX(x);
        this.hpBar.setLayoutY(y - 10);
        this.hpBar.setStyle("-fx-accent: green;");
        this.hpBar.setPrefWidth(100);
        this.hpBar.setPrefHeight(15);
    }

    /**
     * vráti y-ovu suradnicu baru
     * @return y-ova suradnica baru
     */
    public double getY() {
        return this.y;
    }


    /**
     * vráti x-ovu suradnicu baru
     * @return x-ova suradnica baru
     */
    public double getX() {
        return this.x;
    }

    /**
     * posunie x-ovu suradnicu baru o zadani parameter
     * @param x pocet o kolko sa ma posunúť na osi x
     */
    public void moveBarX(double x) {
        this.x = x;
        this.hpBar.setLayoutX(x);
    }

    /**
     * posunie y-ovu suradnicu baru o zadani parameter
     * @param y pocet o kolko sa ma posunúť na osi y
     */
    public void moveBarY(double y) {
        this.y = y;
        this.hpBar.setLayoutY(y);
    }


    /**
     * vrati progressBar ktory reprezentuje životy
     * @return hpBar, progressBar životov
     */
    public ProgressBar getHpBar() {
        return this.hpBar;
    }

    /**
     * posunie hodnotu v healthBare o zadane množstvo životoc
     * @param health počet životov na ktoré treba nastaviť healthBar
     */
    public void changeHealth(int health) {
        this.hpBar.setProgress((double)health / this.maxHp);
    }
}
