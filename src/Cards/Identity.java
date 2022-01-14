package Cards;

import Cards.Card;
import Cards.Role;

/**
 * Classe representant la carte identite.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class Identity extends Card {
    /**
     * Role de la carte.
     * @author Lilsb et AGOUGILE
     */
    private Role role;

    /**
     * Getter du role.
     * @return Le Role de la carte.
     * @author Lilsb et AGOUGILE
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Setter du role.
     * @param value Le Role de la carte.
     * @author Lilsb et AGOUGILE
     */
    public void setRole(Role value) {
        this.role = value;
    }
}
