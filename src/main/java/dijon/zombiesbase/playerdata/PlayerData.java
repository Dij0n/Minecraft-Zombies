package dijon.zombiesbase.playerdata;

import dijon.zombiesbase.weapons.Gun;
import dijon.zombiesbase.weapons.GunType;

public class PlayerData {

    Gun currentlyEquipped;

    public PlayerData(){
        currentlyEquipped = GunType.NONE;
    }


    public Gun getGun() {
        return currentlyEquipped;
    }
    public void setGun(Gun currentlyEquipped) {
        this.currentlyEquipped = currentlyEquipped;
    }
}
