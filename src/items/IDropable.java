package items;



public interface IDropable {
    /**
     * Vrati predmet na vyhodenie s nastavenymi suradnicami dopadu
     * @param x x-ova suradnicami kde ma dopadnut
     * @param y y-ova suradnicami kde ma dopadnut
     * @return vrati predmet na vyhodenie
     */
    Item drop(double x, double y);
}
