package items;

import characters.Hero;


public interface IUsable {
    /**
     * použitie predmetu
     * @param hero postava hráča
     */
    void use(Hero hero);
}
