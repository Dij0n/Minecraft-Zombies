package dijon.zombiesbase.playerdata;

import dijon.zombiesbase.shooting.Gun;
import dijon.zombiesbase.shooting.GunType;

public class PlayerData {

    Gun currentlyEquipped;

    Status status;

    public PlayerData(){
        currentlyEquipped = GunType.NONE;
        status = Status.IDLE;
    }


    public Gun getGun() {
        return currentlyEquipped;
    }
    public void setGun(Gun currentlyEquipped) {
        this.currentlyEquipped = currentlyEquipped;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
